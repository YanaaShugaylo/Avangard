package com.avangard.bratstvo.tasks.details.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.tasks.details.domain.GetTaskDetailsUseCase
import com.avangard.bratstvo.tasks.details.domain.TaskActivateUseCase
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskDetailsUiMapper
import com.avangard.bratstvo.tasks.details.presentation.model.TaskDetailsActionButton
import com.avangard.bratstvo.tasks.details.presentation.model.TaskDetailsUiModel
import com.avangard.bratstvo.tasks.details.presentation.model.TasksStatuses
import kotlinx.coroutines.launch

class TaskDetailsViewModel(
    private val getTaskDetails: GetTaskDetailsUseCase,
    private val taskActivate: TaskActivateUseCase,
    private val taskDetailsUiMapper: TaskDetailsUiMapper
) : ViewModel() {

    val openDoneBottomSheet = MutableLiveData<Boolean>()
    val taskUi = MutableLiveData<TaskDetailsUiModel?>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    fun loadTask(taskId: Int?) {
        baseViewModelDelegate.coroutineScope.launch {
            taskUi.value = if (taskId != null) {
                val taskDetails = getTaskDetails(taskId)
                taskDetailsUiMapper.map(taskDetails)
            } else {
                null
            }
        }
    }

    fun onActionButtonClick() {
        when (taskUi.value?.status) {
            TasksStatuses.NOT_ACTIVATED -> { activateTask() }
            TasksStatuses.ACTIVATED -> { openDoneBottomSheet.value = true }
            else -> {}
        }
    }

    fun navigationComplete() {
        openDoneBottomSheet.value = false
    }

    private fun activateTask() {
        baseViewModelDelegate.coroutineScope.launch {
            val isActivated = taskActivate(taskUi.value!!.id)
            Log.i("myLog", "isActivated = $isActivated")

            if (isActivated) {
                taskUi.value = taskUi.value?.copy(
                    status = TasksStatuses.ACTIVATED,
                    button = TaskDetailsActionButton.getActionButtonModel(TasksStatuses.ACTIVATED)
                )
            }
        }
    }
}