package com.avangard.bratstvo.profile.root.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.profile.root.data.model.UserRatingDto
import com.avangard.bratstvo.profile.root.data.model.UserSkillDto
import retrofit2.http.GET

interface ProfileApi {

    @GET("/api/v1/user/rating")
    suspend fun getRating(): BaseBackendResponse<UserRatingDto>

    @GET("/api/v1/user/skills")
    suspend fun getSkills(): BaseBackendResponse<List<UserSkillDto>>

    @GET("/api/v1/user/balance")
    suspend fun getNewBalance(): BaseBackendResponse<UserNewBalanceDto>

    @GET("/api/v1/user/history")
    suspend fun getHistory(): BaseBackendResponse<UserHistoryDto>


}