package com.avangard.bratstvo.tasks.details.data

import android.util.Log
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.tasks.details.domain.TaskDetailsRepository
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDetailsDtoMapper
import kotlinx.coroutines.withContext
import java.lang.Exception

class TaskDetailsRepositoryImpl(
    private val api: TaskDetailsApi,
    private val taskDetailsDtoMapper: TaskDetailsDtoMapper,
    private val appDispatchers: AppDispatchers
) : TaskDetailsRepository {

    override suspend fun getTaskDetails(taskId: Int) = withContext(appDispatchers.network) {
        var task: TaskDetails? = null

        try {
            val result = api.getTaskDetails(taskId)
            task = taskDetailsDtoMapper.map(result.data)
        } catch (e: Exception) {
        }

        task
    }

    override suspend fun taskActivate(taskId: Int) = withContext(appDispatchers.network) {
        var result: BaseBackendResponse<*>? = null

        try {
            result = api.taskActivate(taskId)
        } catch (e: Exception) {

        }

        result != null
    }
}