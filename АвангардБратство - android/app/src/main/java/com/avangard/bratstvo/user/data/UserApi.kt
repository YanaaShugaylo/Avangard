package com.avangard.bratstvo.user.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.user.data.model.BalanceDto
import com.avangard.bratstvo.user.data.model.UserDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @GET("/api/v1/user")
    suspend fun getUser(): BaseBackendResponse<UserDto>

    @GET("/api/v1/user/balance")
    suspend fun getBalance(@Header("Authorization") token : String): BaseBackendResponse<BalanceDto>

}