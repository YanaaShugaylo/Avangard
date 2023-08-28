package com.avangard.bratstvo.profile.root

import com.avangard.bratstvo.profile.root.data.UserNewBalanceDto
import com.avangard.bratstvo.profile.root.data.UserNewBalanceDtoMapper
import com.avangard.bratstvo.profile.root.data.model.UserRatingDto
import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingDtoMapper
import com.avangard.bratstvo.profile.root.domain.model.UserRating

class UserNewBalanceDtoMapperImpl: UserNewBalanceDtoMapper {


    override fun map(dto: UserNewBalanceDto) = UserNewBalance(dto.balance, dto.totalBalance, dto.totalSpent, dto.totalEarned)
}
