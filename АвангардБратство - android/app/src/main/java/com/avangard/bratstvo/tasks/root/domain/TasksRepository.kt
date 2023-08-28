package com.avangard.bratstvo.tasks.root.domain

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.root.domain.model.Task
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory

interface TasksRepository {

    suspend fun getCategories(): List<TasksCategory>
    suspend fun getTasks(categoryId: Int): List<TaskDetails>
    suspend fun getTasksDaily(): List<Task>
}