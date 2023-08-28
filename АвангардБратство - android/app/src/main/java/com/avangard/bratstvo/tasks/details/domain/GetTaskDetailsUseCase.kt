package com.avangard.bratstvo.tasks.details.domain

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails

class GetTaskDetailsUseCase(private val taskDetailsRepository: TaskDetailsRepository) {

    suspend operator fun invoke(taskId: Int): TaskDetails? = taskDetailsRepository.getTaskDetails(taskId)
}