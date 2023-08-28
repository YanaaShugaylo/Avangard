package com.avangard.bratstvo.education.details.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.education.root.data.model.LessonDto
import retrofit2.http.GET
import retrofit2.http.Path

interface LessonDetailsApi {

    @GET("/api/v1/lessons/{id}")
    suspend fun getLessonDetails(@Path("id") lessonId: Int): BaseBackendResponse<LessonDto>
}