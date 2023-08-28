package com.avangard.bratstvo.profile.root.data

import com.avangard.bratstvo.profile.root.data.model.UserRatingDto
import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.domain.model.UserRating

interface UserNewBalanceDtoMapper {
    fun map(dto: UserNewBalanceDto): UserNewBalance
}
