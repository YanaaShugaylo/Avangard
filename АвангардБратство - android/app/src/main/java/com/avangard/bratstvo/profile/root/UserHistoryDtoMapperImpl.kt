package com.avangard.bratstvo.profile.root

import com.avangard.bratstvo.profile.root.data.UserHistoryDto
import com.avangard.bratstvo.profile.root.data.UserHistoryDtoMapper
import com.avangard.bratstvo.profile.root.data.UserNewBalanceDto
import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.presentation.UserHistory

class UserHistoryDtoMapperImpl: UserHistoryDtoMapper {
    //fun map(dto: UserNewBalanceDto) = UserNewBalance(dto.balance, dto.totalBalance, dto.totalSpent, dto.totalEarned)
    override fun map(dto: UserHistoryDto) = UserHistory (
       dto.history
           )
}
