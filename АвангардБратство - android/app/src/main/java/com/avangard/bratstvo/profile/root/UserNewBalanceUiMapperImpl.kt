package com.avangard.bratstvo.profile.root

import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingUiMapper
import com.avangard.bratstvo.profile.root.domain.model.UserRating
import com.avangard.bratstvo.profile.root.presentation.UserNewBalanceUiMapper
import com.avangard.bratstvo.profile.root.presentation.UserNewBalanceUiModel
import com.avangard.bratstvo.profile.root.presentation.model.UserRatingUiModel

class UserNewBalanceUiMapperImpl : UserNewBalanceUiMapper  {



    override fun map(domainModel: UserNewBalance) = UserNewBalanceUiModel (
        domainModel.balance,
        domainModel.totalBalance,
        domainModel.totalEarned,
        domainModel.totalSpent
    )
}
