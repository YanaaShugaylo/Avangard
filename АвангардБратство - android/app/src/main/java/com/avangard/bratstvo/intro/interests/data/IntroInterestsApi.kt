package com.avangard.bratstvo.intro.interests.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.intro.interests.data.model.InterestsListDto
import com.avangard.bratstvo.intro.interests.data.model.IntroSecondStepDto
import com.avangard.bratstvo.user.data.model.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface IntroInterestsApi {

    @GET("/api/v1/questionnaire/second_step")
    suspend fun getInterests(): BaseBackendResponse<InterestsListDto>

    @POST("/api/v1/questionnaire/second_step")
    suspend fun saveInterests(
        @Body introFirstStepDto: IntroSecondStepDto
    ): BaseBackendResponse<UserDto>
}