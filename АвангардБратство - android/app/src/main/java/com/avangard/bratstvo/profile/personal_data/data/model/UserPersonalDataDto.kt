package com.avangard.bratstvo.profile.personal_data.data.model

import com.avangard.bratstvo.user.data.model.HobbySimpleDto
import com.avangard.bratstvo.user.data.model.InterestSimpleDto
import com.google.gson.annotations.SerializedName

class UserPersonalDataDto(
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