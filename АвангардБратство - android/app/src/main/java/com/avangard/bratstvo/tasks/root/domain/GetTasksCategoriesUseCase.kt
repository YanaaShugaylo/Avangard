package com.avangard.bratstvo.tasks.root.domain

import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory

class GetTasksCategoriesUseCase(private val tasksRepository: TasksRepository) {

    suspend operator fun invoke(): List<TasksCategory> = tasksRepository.getCategories()
}