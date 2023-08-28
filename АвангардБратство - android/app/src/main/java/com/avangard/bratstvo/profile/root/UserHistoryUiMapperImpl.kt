package com.avangard.bratstvo.profile.root

import com.avangard.bratstvo.profile.root.presentation.UserHistory
import com.avangard.bratstvo.profile.root.presentation.UserHistoryUiMapper
import com.avangard.bratstvo.profile.root.presentation.UserHistoryUiModel

class UserHistoryUiMapperImpl: UserHistoryUiMapper {
    override fun map(domainModel: UserHistory) = UserHistoryUiModel(
        domainModel.history
    )

}
