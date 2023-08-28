package com.avangard.bratstvo.tasks.done.presentation

import android.content.Context
import android.net.Uri
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.core.net.toFile
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.domain.UploadFileUseCase
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.tasks.details.domain.GetTaskDetailsUseCase
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.done.domain.SendTaskResultUseCase
import com.avangard.bratstvo.tasks.done.domain.mapper.TaskDoneItemMapper
import com.avangard.bratstvo.tasks.done.domain.model.TaskResultData
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel
import kotlinx.coroutines.launch

class TaskDoneViewModel(
    private val getTaskDetails: GetTaskDetailsUseCase,
    private val uploadFile: UploadFileUseCase,
    private val sendTaskResult: SendTaskResultUseCase,
    private val taskDoneItemMapper: TaskDoneItemMapper
) : ViewModel() {

    val sendResultDone = MutableLiveData<String>()
    val pickFile = MutableLiveData(false)
    val taskUi = MutableLiveData<List<TaskDoneItemModel>>()

    var comment = ""

    var isLoading = MutableLiveData(false)

    private var task: TaskDetails? = null
    private var taskResultData: TaskResultData? = null

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    fun loadTask(taskId: Int?) {
        baseViewModelDelegate.coroutineScope.launch {
            taskUi.value = if (taskId != null) {
                task = getTaskDetails(taskId)
                taskDoneItemMapper.map(task)
            } else {
                null
            }
        }
    }

    fun onItemClick(item: TaskDoneItemModel) {
        when(item) {
            is TaskDoneItemModel.ButtonFile -> {
                pickFile.value = true
            }
            is TaskDoneItemModel.ButtonAction -> {
                if (taskResultData != null && isCommentOkay()) {
                    baseViewModelDelegate.coroutineScope.launch {
                        taskResultData!!.comment = comment
                        val result = sendTaskResult(taskResultData!!)

                        if (result) {
                            sendResultDone.value = "Результат успешно отправлен"
                        }
                    }
                }
            }
        }
    }

    fun filePicked(uri: Uri, context: Context) {
        isLoading.value = true
        val fileType: String? = context.contentResolver.getType(uri)
        setFileButtonFileTitle(
            "${uri.lastPathSegment}." + MimeTypeMap.getSingleton().getExtensionFromMimeType(fileType)
        )

        taskResultData = TaskResultData(
            task?.taskUserData?.id ?: 0,
            "",
            ""
        )

        baseViewModelDelegate.coroutineScope.launch {
            taskResultData?.fileUuid = uploadFile(uri, "task")

            val newUi = ArrayList<TaskDoneItemModel>()
            newUi.addAll(taskUi.value!!)

            for (i in newUi.indices) {
                if (newUi[i] is TaskDoneItemModel.ButtonAction) {
                    newUi[i] = TaskDoneItemModel.ButtonAction(
                        (newUi[i] as TaskDoneItemModel.ButtonAction).textRes,
                        isContentLoaded = true
                    )
                }
            }

            taskUi.value = newUi

            isLoading.value = false
        }
    }

    fun setCommentText(text: String) {
        comment = text
    }

    private fun  setFileButtonFileTitle(fileName: String) {
        if (taskUi.value != null) {
            val taskUiNew = ArrayList<TaskDoneItemModel>()
            taskUiNew.addAll(taskUi.value!!)
            for (i in taskUiNew.indices) {
                if (taskUiNew[i] is TaskDoneItemModel.ButtonFile) {
                    taskUiNew[i] = TaskDoneItemModel.ButtonFile(fileName)
                    break
                }
            }

            taskUi.value = taskUiNew
        }

    }

    private fun isCommentOkay(): Boolean {
        return taskUi.value?.let {
            var isOkay = true

            for (field in it) {
                if (field is TaskDoneItemModel.InputField) {
                    isOkay = comment.isNotEmpty()
                }
            }

            isOkay
        } ?: false
    }
}