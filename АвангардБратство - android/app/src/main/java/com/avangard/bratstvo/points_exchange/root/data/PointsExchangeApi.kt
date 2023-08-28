package com.avangard.bratstvo.points_exchange.root.data

import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.points_exchange.root.data.model.PrizeDto
import com.avangard.bratstvo.points_exchange.root.data.model.PrizesCategoryDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PointsExchangeApi {

    @GET("/api/v1/prizes/categories")
    suspend fun getPrizesCategories(): BaseBackendResponse<List<PrizesCategoryDto>>

    @GET("/api/v1/prizes")
    suspend fun getPrizes(
        @Query("category") categoryId: Int? = null,
        @Query("bought") getBought: Int? = null,
        @Query("page") page: Int? = null
    ): BaseBackendResponse<List<PrizeDto>>
}