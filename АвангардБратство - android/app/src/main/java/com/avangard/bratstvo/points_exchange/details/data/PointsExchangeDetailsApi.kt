package com.avangard.bratstvo.points_exchange.details.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.points_exchange.details.data.model.PrizeDetailsDto
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PointsExchangeDetailsApi {

    @GET("/api/v1/prizes/{id}")
    suspend fun getPrizeDetails(@Path("id") prizeId: Int): BaseBackendResponse<PrizeDetailsDto>

    @POST("/api/v1/prizes/{id}/buy")
    suspend fun buyPrize(@Path("id") prizeId: Int): BaseBackendResponse<Any>
}