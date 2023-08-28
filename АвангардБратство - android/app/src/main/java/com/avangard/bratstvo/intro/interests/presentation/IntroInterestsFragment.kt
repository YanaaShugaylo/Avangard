package com.avangard.bratstvo.intro.interests.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.IntroInterestsFragmentBinding
import com.avangard.bratstvo.intro.interests.presentation.adapter.IntroInterestsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroInterestsFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: IntroInterestsFragmentBinding? = null
    private val viewModel by viewModel<IntroInterestsViewModel>()

    private lateinit var chipsAdapter: IntroInterestsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = IntroInterestsFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.intro_about_title),
            ToolbarButtonsStates.NOTHING
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            chipsAdapter = IntroInterestsAdapter(interestsCg)

            viewModel.interestsUiModel.observe(viewLifecycleOwner) {
                chipsAdapter.items = it
            }

            viewModel.readyToNavigateNext.observe(viewLifecycleOwner) {
                if (it) {
                    navigateNext()
                }
            }

            interestsActionButton.setOnClickListener {
                viewModel.onActionButtonClick()
            }
        }
    }

    private fun navigateNext() {
        val action =
            IntroInterestsFragmentDirections
                .actionInterestsFragmentToHomeFragment()
        binding?.root?.findNavController()?.navigate(action)
    }
}