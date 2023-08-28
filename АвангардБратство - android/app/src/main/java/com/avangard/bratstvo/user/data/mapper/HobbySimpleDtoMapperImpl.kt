package com.avangard.bratstvo.user.data.mapper

import com.avangard.bratstvo.user.data.model.HobbySimpleDto
import com.avangard.bratstvo.user.domain.mapper.HobbySimpleDtoMapper
import com.avangard.bratstvo.user.domain.model.HobbySimpleModel

class HobbySimpleDtoMapperImpl : HobbySimpleDtoMapper {

    override fun map(dto: HobbySimpleDto) = HobbySimpleModel(
        dto.title
    )
}