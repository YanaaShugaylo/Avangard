package com.avangard.bratstvo.education.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import coil.load
import com.avangard.bratstvo.R
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.EducationDetailsFragmentBinding
import com.avangard.bratstvo.education.details.presentation.model.LessonDetailsUiModel
import com.avangard.bratstvo.profile.root.presentation.ProfileFragmentDirections
import com.avangard.bratstvo.tests.root.presentation.TestFragmentDirections
import org.koin.androidx.viewmodel.ext.android.viewModel

class EducationDetailsFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: EducationDetailsFragmentBinding? = null

    private val viewModel by viewModel<EducationDetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = EducationDetailsFragmentBinding.inflate(inflater)

        val lessonId = if (arguments != null) {
            EducationDetailsFragmentArgs.fromBundle(requireArguments()).lessonId
        } else {
            null
        }

        viewModel.loadTask(lessonId)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.education_details_title),
            ToolbarButtonsStates.COMMON_STATE,
            View.GONE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.lessonUi.observe(viewLifecycleOwner) {
            if (it != null) {
                setUi(it)
            } else {
                goBack()
            }
        }
    }

    private fun setUi(uiModel: LessonDetailsUiModel) {
        binding?.run {
            btnGoToTestFragment.setOnClickListener {
                val action =
                    EducationDetailsFragmentDirections.actionEducationDetailsFragmentToTestFragment()
                binding?.root?.findNavController()?.navigate(action)
            }
        }
        binding?.run {




            educationDetailsHeaderIv.load(uiModel.backgroundImageLink)
            educationDetailsTitleTv.text = uiModel.title

            educationContentWv.webViewClient = WebViewClient()
            educationContentWv.webChromeClient = WebChromeClient()

            educationContentWv.settings.javaScriptEnabled = true
            educationContentWv.loadUrl(uiModel.descriptionLink)
        }
    }

    private fun goBack() {
        requireActivity().onBackPressed()
    }
}