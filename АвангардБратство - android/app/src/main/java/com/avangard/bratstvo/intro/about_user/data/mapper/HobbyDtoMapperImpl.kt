package com.avangard.bratstvo.intro.about_user.data.mapper

import com.avangard.bratstvo.intro.about_user.data.model.HobbyDto
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbyDtoMapper
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby

class HobbyDtoMapperImpl : HobbyDtoMapper {

    override fun map(dto: HobbyDto) = Hobby(
        dto.id,
        dto.title,
        dto.sortOrder,
        dto.isActive
    )
}