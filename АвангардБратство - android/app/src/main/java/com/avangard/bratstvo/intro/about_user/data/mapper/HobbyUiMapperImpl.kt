package com.avangard.bratstvo.intro.about_user.data.mapper

import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbyUiMapper
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby
import com.avangard.bratstvo.intro.about_user.presentation.model.HobbyUiModel

class HobbyUiMapperImpl : HobbyUiMapper {

    override fun map(domainModel: Hobby) = HobbyUiModel(
        domainModel.id,
        domainModel.title
    )
}