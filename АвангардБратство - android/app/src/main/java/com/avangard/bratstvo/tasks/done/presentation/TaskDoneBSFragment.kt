package com.avangard.bratstvo.tasks.done.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.avangard.bratstvo.base.root.presentation.BaseFragmentDelegate
import com.avangard.bratstvo.databinding.TaskDoneBsFragmentBinding
import com.avangard.bratstvo.tasks.details.presentation.TaskDetailsFragmentArgs
import com.avangard.bratstvo.tasks.done.presentation.adapter.TaskDoneAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper

class TaskDoneBSFragment : BottomSheetDialogFragment() {

    private lateinit var baseFragmentDelegate: BaseFragmentDelegate
    private var binding: TaskDoneBsFragmentBinding? = null

    private val viewModel by viewModel<TaskDoneViewModel>()

    private val adapter: TaskDoneAdapter by lazy {
        TaskDoneAdapter(viewModel::onItemClick, viewModel::setCommentText)
    }

    private val getPermissions = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        Log.i("myLog", "permission granted")
    }

    class GalleryContract : ActivityResultContract<String, Uri?>() {

//        override fun createIntent(context: Context, input: String?): Intent {
////            return Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//            return Intent(Intent.ACTION_GET_CONTENT)
//                .setType("image/*, video/*")
//        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? = when {
            resultCode != Activity.RESULT_OK -> null
            else -> intent?.data
        }

        override fun createIntent(context: Context, input: String): Intent {
            return Intent(Intent.ACTION_GET_CONTENT)
                .setType("image/*, video/*")
        }
    }

    private val getFile = registerForActivityResult(GalleryContract()) { uri ->
        uri?.let {
            viewModel.filePicked(it, requireContext())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TaskDoneBsFragmentBinding.inflate(inflater)

        val taskId = if (arguments != null) {
            TaskDetailsFragmentArgs.fromBundle(requireArguments()).taskId
        } else {
            null
        }

        viewModel.loadTask(taskId)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.run {
            contentRv.adapter = adapter

            viewModel.taskUi.observe(viewLifecycleOwner) {
                adapter.items = it
            }

            viewModel.pickFile.observe(viewLifecycleOwner) {
                if (it) {
                    showFilePicker()
                }
            }

            viewModel.sendResultDone.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    sendResultDone(it)
                }
            }

            viewModel.isLoading.observe(viewLifecycleOwner) {
                this.loaderBackgroundView.isVisible = it
                this.loaderPb.isVisible = it
            }

            getPermissions.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)

            loaderBackgroundView.updateLayoutParams<ConstraintLayout.LayoutParams> {
                height = root.height
            }
        }
    }

    private fun showFilePicker() {
        getFile.launch("image/*")
    }

    private fun sendResultDone(message: String) {
        MainActivityInteractionsHelper.showMessage(message)
        requireActivity().onBackPressed()
    }
}