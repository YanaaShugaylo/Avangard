package com.avangard.bratstvo.tasks.done.domain

import com.avangard.bratstvo.tasks.done.domain.model.TaskResultData

interface TaskDoneRepository {

    suspend fun sendResult(taskResultData: TaskResultData): Boolean
}