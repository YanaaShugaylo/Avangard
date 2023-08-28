package com.avangard.bratstvo.tasks.root.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.tasks.root.data.model.TasksCategoryDto
import com.avangard.bratstvo.tasks.root.data.model.TaskDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TasksApi {

    @GET("/api/v1/tasks/categories")
    suspend fun getCategories(): BaseBackendResponse<List<TasksCategoryDto>>

    @GET("/api/v1/tasks/categories/{id}")
    suspend fun getTasks(
        @Path("id") categoryId: Int
    ): BaseBackendResponse<TasksCategoryDto>

    @GET("/api/v1/tasks/daily")
    suspend fun getTasksDaily(): BaseBackendResponse<List<TaskDto>>
}