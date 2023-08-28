package com.avangard.bratstvo.home.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.intro.about_user.data.model.HobbiesListDto
import com.avangard.bratstvo.intro.about_user.data.model.IntroFirstStepDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HomeApi {

    @GET("/api/v1/questionnaire/first_step")
    suspend fun getHobbies(): BaseBackendResponse<HobbiesListDto>

    @POST("/api/v1/questionnaire/first_step")
    suspend fun saveHobbies(
        @Body introFirstStepDto: IntroFirstStepDto
    )
}