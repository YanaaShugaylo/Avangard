package com.avangard.bratstvo.profile.root.data.mapper

import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingUiMapper
import com.avangard.bratstvo.profile.root.domain.model.UserRating
import com.avangard.bratstvo.profile.root.presentation.model.UserRatingUiModel

class UserRatingUiMapperImpl : UserRatingUiMapper {

    override fun map(domainModel: UserRating) = UserRatingUiModel(
        domainModel.total,
        domainModel.position
    )
}