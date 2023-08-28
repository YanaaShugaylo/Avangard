package com.avangard.bratstvo.intro.about_user.domain.mapper

import com.avangard.bratstvo.intro.about_user.domain.model.Hobby
import com.avangard.bratstvo.intro.about_user.presentation.model.HobbyUiModel

interface HobbyUiMapper {

    fun map(domainModel: Hobby): HobbyUiModel
}