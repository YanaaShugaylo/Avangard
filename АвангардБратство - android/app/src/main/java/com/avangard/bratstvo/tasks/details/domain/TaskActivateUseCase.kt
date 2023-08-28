package com.avangard.bratstvo.tasks.details.domain

class TaskActivateUseCase(private val taskDetailsRepository: TaskDetailsRepository) {

    suspend operator fun invoke(taskId: Int): Boolean = taskDetailsRepository.taskActivate(taskId)
}