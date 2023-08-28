package com.avangard.bratstvo.tasks.done.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.tasks.done.data.model.TaskResultDataDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskDoneApi {

    @POST("/api/v1/tasks/{$PATH_TASK_USER_ID}/result")
    suspend fun sendTaskResult(
        @Path(PATH_TASK_USER_ID) taskUserId: Int,
        @Body taskDoneDataDto: TaskResultDataDto
    ): BaseBackendResponse<*>

    companion object {
        private const val PATH_TASK_USER_ID = "task_user_id"
    }
}