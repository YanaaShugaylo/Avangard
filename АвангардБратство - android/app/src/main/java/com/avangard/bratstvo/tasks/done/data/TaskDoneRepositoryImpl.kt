package com.avangard.bratstvo.tasks.done.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.tasks.done.domain.TaskDoneRepository
import com.avangard.bratstvo.tasks.done.domain.mapper.TaskResultDataDtoMapper
import com.avangard.bratstvo.tasks.done.domain.model.TaskResultData
import kotlinx.coroutines.withContext
import java.lang.Exception

class TaskDoneRepositoryImpl(
    private val api: TaskDoneApi,
    private val taskResultDataDtoMapper: TaskResultDataDtoMapper,
    private val appDispatchers: AppDispatchers
) : TaskDoneRepository {

    override suspend fun sendResult(taskResultData: TaskResultData) = withContext(appDispatchers.network) {
        var result: BaseBackendResponse<*>? = null

        try {
            result = api.sendTaskResult(taskResultData.userTaskId, taskResultDataDtoMapper.map(taskResultData))
        } catch (e: Exception) {

        }

        result != null
    }
}