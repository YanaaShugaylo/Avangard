package com.avangard.bratstvo.education.root.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.education.root.data.model.EducationCategoryDto
import com.avangard.bratstvo.education.root.data.model.LessonsListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface EducationApi {

    @GET("/api/v1/lessons/categories")
    suspend fun getEducationCategories(): BaseBackendResponse<List<EducationCategoryDto>>

    @GET("/api/v1/lessons/categories/{id}")
    suspend fun getLessons(
        @Path("id") categoryId: Int
    ): BaseBackendResponse<LessonsListDto>
}