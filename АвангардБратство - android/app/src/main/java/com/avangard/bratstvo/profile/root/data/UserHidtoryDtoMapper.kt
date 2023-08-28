package com.avangard.bratstvo.profile.root.data

import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.presentation.UserHistory

interface UserHistoryDtoMapper {
    fun map(dto: UserHistoryDto): UserHistory
}
