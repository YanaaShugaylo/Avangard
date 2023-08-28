package com.avangard.bratstvo.tasks.done.domain

import com.avangard.bratstvo.tasks.done.domain.model.TaskResultData

class SendTaskResultUseCase(private val taskDoneRepository: TaskDoneRepository) {

    suspend operator fun invoke(
        taskResultData: TaskResultData
    ): Boolean = taskDoneRepository.sendResult(taskResultData)
}