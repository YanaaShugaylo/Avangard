package com.avangard.bratstvo.base.root.data

import com.avangard.bratstvo.base.root.data.model.UploadResponseDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BaseRequestsApi {

    @Multipart
    @POST("/api/v1/upload")
    suspend fun uploadImage(
        @Part("section") description: RequestBody,
        @Part file: MultipartBody.Part
    ): BaseBackendResponse<UploadResponseDto>
}