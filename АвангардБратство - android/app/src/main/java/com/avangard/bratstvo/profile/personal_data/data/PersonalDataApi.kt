package com.avangard.bratstvo.profile.personal_data.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.profile.personal_data.data.model.UserPersonalDataDto
import retrofit2.http.GET

interface PersonalDataApi {

    @GET("/api/v1/user/personal_data")
    suspend fun getData(): BaseBackendResponse<UserPersonalDataDto>
}