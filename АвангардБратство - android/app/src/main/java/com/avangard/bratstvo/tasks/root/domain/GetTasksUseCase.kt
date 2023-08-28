package com.avangard.bratstvo.tasks.root.domain

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails

class GetTasksUseCase(private val tasksRepository: TasksRepository) {
    suspend operator fun invoke(categoryId: Int): List<TaskDetails> = tasksRepository.getTasks(categoryId)
}