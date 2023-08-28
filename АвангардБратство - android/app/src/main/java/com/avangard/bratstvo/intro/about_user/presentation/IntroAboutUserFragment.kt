package com.avangard.bratstvo.intro.about_user.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.IntroAboutuserFragmentBinding
import com.avangard.bratstvo.intro.about_user.presentation.adapter.IntroHobbiesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IntroAboutUserFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: IntroAboutuserFragmentBinding? = null
    private val viewModel by viewModel<IntroAboutUserViewModel>()

    private lateinit var chipsAdapter: IntroHobbiesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = IntroAboutuserFragmentBinding.inflate(inflater)

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
            chipsAdapter = IntroHobbiesAdapter(hobbiesCg, viewModel::dataHasChanged)

            aboutuserEt.doAfterTextChanged { viewModel.dataHasChanged(it.toString()) }

            viewModel.hobbiesUiModel.observe(viewLifecycleOwner) {
                chipsAdapter.items = it
            }

            viewModel.readyToNavigateNext.observe(viewLifecycleOwner) {
                if (it) {
                    navigateNext()
                }
            }

            viewModel.isUserDataEmpty.observe(viewLifecycleOwner) {
                if (it) {
                    aboutUserActionButton.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.button_bordered_bg)
                    aboutUserActionButton.setTextColor(
                        ContextCompat.getColor(requireContext(), R.color.base_button_text_color)
                    )
                    aboutUserActionButton.text = getString(R.string.intro_about_button_skip_text)
                } else {
                    aboutUserActionButton.background =
                        ContextCompat.getDrawable(requireContext(), R.drawable.base_button_bg)
                    aboutUserActionButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    aboutUserActionButton.text = getString(R.string.intro_about_button_next_text)
                }
            }

            aboutUserActionButton.setOnClickListener {
                viewModel.onActionButtonClick(aboutuserEt.text.toString())
            }
        }
    }

    private fun navigateNext() {
        val action = IntroAboutUserFragmentDirections.actionAboutUserFragmentToInterestsFragment()
        binding?.root?.findNavController()?.navigate(action)
    }
}