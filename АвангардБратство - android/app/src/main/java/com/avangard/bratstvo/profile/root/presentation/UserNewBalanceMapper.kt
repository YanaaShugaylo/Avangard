package com.avangard.bratstvo.profile.root.presentation

import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.domain.model.UserRating
import com.avangard.bratstvo.profile.root.presentation.model.UserRatingUiModel

interface UserNewBalanceUiMapper {
    fun map(domainModel: UserNewBalance): UserNewBalanceUiModel
}
