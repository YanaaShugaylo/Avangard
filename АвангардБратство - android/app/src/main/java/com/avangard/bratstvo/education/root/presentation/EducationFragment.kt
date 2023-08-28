package com.avangard.bratstvo.education.root.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.EducationFragmentBinding
import com.avangard.bratstvo.education.root.presentation.adapter.EducationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class EducationFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: EducationFragmentBinding? = null
    private val viewModel by viewModel<EducationViewModel>()

    private val adapter: EducationAdapter by lazy {
        EducationAdapter(viewModel::onItemClick)
    }

    private val onBackPressedCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(false) {
            override fun handleOnBackPressed() {
                viewModel.onBackClick()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EducationFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.education_title),
            ToolbarButtonsStates.MAIN_STATE,
            View.VISIBLE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            contentRv.adapter = adapter

            viewModel.educationUi.observe(viewLifecycleOwner) {
                adapter.items = it
            }

            viewModel.navigateToLesson.observe(viewLifecycleOwner) {
                if (it > 0) {
                    navigateToLesson(it)
                }
            }

            requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
            onBackPressedCallback.isEnabled = false

            viewModel.isCategoryScreen.observe(viewLifecycleOwner) {
                if (it) {
                    baseFragmentDelegate.screenToolbarButtonsState = ToolbarButtonsStates.COMMON_STATE
                    onBackPressedCallback.isEnabled = true
                } else {
                    baseFragmentDelegate.screenToolbarButtonsState = ToolbarButtonsStates.MAIN_STATE
                    onBackPressedCallback.isEnabled = false
                }

                baseFragmentDelegate.notifyDataSetChanged()
            }

            viewModel.isOnline.observe(viewLifecycleOwner) {
                this.offlineTv.isVisible = !it
            }
        }
    }

    private fun navigateToLesson(lessonId: Int) {
        val action =
            EducationFragmentDirections.actionEducationFragmentToEducationDetailsFragment(lessonId)
        binding?.root?.findNavController()?.navigate(action)
        onBackPressedCallback.isEnabled = false
        viewModel.navigationComplete()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        onBackPressedCallback.isEnabled = false
    }
}