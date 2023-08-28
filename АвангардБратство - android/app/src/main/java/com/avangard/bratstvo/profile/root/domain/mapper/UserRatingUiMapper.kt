package com.avangard.bratstvo.profile.root.domain.mapper

import com.avangard.bratstvo.profile.root.domain.model.UserRating
import com.avangard.bratstvo.profile.root.presentation.model.UserRatingUiModel

interface UserRatingUiMapper {

    fun map(domainModel: UserRating): UserRatingUiModel
}