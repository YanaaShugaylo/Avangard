package com.avangard.bratstvo.tasks.details.domain

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails

interface TaskDetailsRepository {

    suspend fun getTaskDetails(taskId: Int): TaskDetails?
    suspend fun taskActivate(taskId: Int): Boolean
}