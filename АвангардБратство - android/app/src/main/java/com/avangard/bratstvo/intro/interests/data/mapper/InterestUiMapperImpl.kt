package com.avangard.bratstvo.intro.interests.data.mapper

import com.avangard.bratstvo.intro.interests.domain.mapper.InterestUiMapper
import com.avangard.bratstvo.intro.interests.domain.model.Interest
import com.avangard.bratstvo.intro.interests.presentation.model.InterestUiModel

class InterestUiMapperImpl : InterestUiMapper {

    override fun map(domainModel: Interest) = InterestUiModel(
        domainModel.id,
        domainModel.title
    )
}