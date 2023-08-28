package com.avangard.bratstvo.tasks.details.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.tasks.details.data.model.TaskDetailsDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TaskDetailsApi {

    @GET("/api/v1/tasks/{id}")
    suspend fun getTaskDetails(@Path("id") taskId: Int): BaseBackendResponse<TaskDetailsDto>

    @POST("/api/v1/tasks/{id}/activate")
    suspend fun taskActivate(@Path("id") taskId: Int): BaseBackendResponse<*>
}