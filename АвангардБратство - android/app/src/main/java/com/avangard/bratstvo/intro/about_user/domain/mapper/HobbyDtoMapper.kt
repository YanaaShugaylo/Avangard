package com.avangard.bratstvo.intro.about_user.domain.mapper

import com.avangard.bratstvo.intro.about_user.data.model.HobbyDto
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby

interface HobbyDtoMapper {

    fun map(dto: HobbyDto): Hobby
}