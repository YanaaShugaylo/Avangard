package com.avangard.bratstvo.base.root.data

import com.avangard.bratstvo.authorization.root.data.model.AuthUserDataDto
import com.avangard.bratstvo.user.data.model.BalanceDto
import com.google.gson.annotations.SerializedName

class BaseBackendResponse<T>(
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("data")
    val data: T,
    @SerializedName("pagination")
    val pagination: PaginationDto,
    val error: ErrorDto
) {

    data class PaginationDto(
        val count: Int,
        val total: Int,
        val perPage: Int,
        val currentPage: Int,
        val totalPages: Int
    )

    sealed class ErrorDto(
        val code: String,
        val message: String
    )
}