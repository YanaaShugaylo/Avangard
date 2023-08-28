package com.avangard.bratstvo.profile.personal_data.domain.model

import com.avangard.bratstvo.user.domain.model.HobbySimpleModel
import com.avangard.bratstvo.user.domain.model.InterestSimpleModel

class UserPersonalData(
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