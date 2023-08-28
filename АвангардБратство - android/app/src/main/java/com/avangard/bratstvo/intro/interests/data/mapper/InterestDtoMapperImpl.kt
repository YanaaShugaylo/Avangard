package com.avangard.bratstvo.intro.interests.data.mapper

import com.avangard.bratstvo.intro.interests.data.model.InterestDto
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestDtoMapper
import com.avangard.bratstvo.intro.interests.domain.model.Interest

class InterestDtoMapperImpl : InterestDtoMapper {

    override fun map(dto: InterestDto) = Interest(
        dto.id,
        dto.title,
        dto.sortOrder,
        dto.isActive
    )
}