package com.avangard.bratstvo.tasks.details.presentation

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
import com.avangard.bratstvo.base.extensions.presentation.fragment.getColor
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates
import com.avangard.bratstvo.databinding.TaskDetailsFragmentBinding
import com.avangard.bratstvo.tasks.details.presentation.model.TaskDetailsUiModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TaskDetailsFragment : Fragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: TaskDetailsFragmentBinding? = null

    private val viewModel by viewModel<TaskDetailsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TaskDetailsFragmentBinding.inflate(inflater)

        baseFragmentDelegate = BaseFragmentDelegate(
            getString(R.string.tasks_details_title),
            ToolbarButtonsStates.COMMON_STATE,
            View.GONE
        )

        baseFragmentDelegate.initialize()

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.taskUi.observe(viewLifecycleOwner) {
            if (it != null) {
                setUi(it)
            } else {
                goBack()
            }
        }

        viewModel.openDoneBottomSheet.observe(viewLifecycleOwner) {
            if (it) {
                openDoneBottomSheet(viewModel.taskUi.value?.id ?: 0)
            }
        }

        binding?.taskDetailsActionButton?.setOnClickListener {
            viewModel.onActionButtonClick()
        }
    }

    override fun onResume() {
        super.onResume()

        val taskId = if (arguments != null) {
            TaskDetailsFragmentArgs.fromBundle(requireArguments()).taskId
        } else {
            null
        }

        viewModel.loadTask(taskId)
    }

    private fun setUi(uiModel: TaskDetailsUiModel) {
        binding?.run {
            taskDetailsHeaderIv.load(uiModel.backgroundImageLink)
            taskDetailsTitleTv.text = uiModel.title
            taskDetailsPointsTv.text =
                resources.getQuantityString(R.plurals.tasks_common_points_pattern, uiModel.points, uiModel.points)
            taskDetailsTermTv.text = uiModel.dateStatusText
            taskDetailsActionButton.run {
                text = getString(uiModel.button.stringRes)
                setTextColor(getColor(requireContext(), uiModel.button.textColorRes))
                taskDetailsActionButton.setBackgroundResource(uiModel.button.backgroundRes)
            }

            taskDetailsContentWv.webViewClient = WebViewClient()
            taskDetailsContentWv.webChromeClient = WebChromeClient()

            taskDetailsContentWv.settings.javaScriptEnabled = true
            taskDetailsContentWv.loadUrl(uiModel.descriptionLink)
        }
    }

    private fun goBack() {
        requireActivity().onBackPressed()
    }

    private fun openDoneBottomSheet(taskId: Int) {
        val action =
            TaskDetailsFragmentDirections
                .actionTaskDetailsFragmentToTaskDoneBSFragment(taskId)
        binding?.root?.findNavController()?.navigate(action)
        viewModel.navigationComplete()
    }
}