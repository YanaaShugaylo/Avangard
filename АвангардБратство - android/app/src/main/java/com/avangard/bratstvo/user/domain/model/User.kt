package com.avangard.bratstvo.user.domain.model

import com.avangard.bratstvo.user.data.model.HobbySimpleDto
import com.avangard.bratstvo.user.data.model.InterestSimpleDto

class User(
    val birthDate: String,
    val education: String,
    val about: String,
    val city: String,
    val fullName: String,
    val balance: Int,
    val photoUrl: String,
    val hobbies: List<HobbySimpleModel>,
    val interests: List<InterestSimpleModel>
)