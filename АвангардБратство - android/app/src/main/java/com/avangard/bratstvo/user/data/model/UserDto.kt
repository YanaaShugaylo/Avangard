package com.avangard.bratstvo.user.data.model

import com.google.gson.annotations.SerializedName

data class UserDto(
    @SerializedName("birth_date")
    val birthDate: String?,
    val education: String?,
    val about: String?,
    val city: String?,
    @SerializedName("full_name")
    val fullName: String?,
    val balance: Int,
    @SerializedName("photo")
    val photoUrl: String?,
    val hobbies: List<HobbySimpleDto>,
    val interests: List<InterestSimpleDto>
)