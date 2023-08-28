package com.avangard.bratstvo.profile.root.presentation

import com.avangard.bratstvo.profile.root.domain.UserNewBalance

interface UserHistoryUiMapper {
    fun map(domainModel: UserHistory): UserHistoryUiModel
}
