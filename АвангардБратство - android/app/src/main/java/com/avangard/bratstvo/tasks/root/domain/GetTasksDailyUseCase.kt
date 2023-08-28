package com.avangard.bratstvo.tasks.root.domain

import com.avangard.bratstvo.tasks.root.domain.model.Task

class GetTasksDailyUseCase(private val tasksRepository: TasksRepository) {

    suspend operator fun invoke(): List<Task> = tasksRepository.getTasksDaily()
}