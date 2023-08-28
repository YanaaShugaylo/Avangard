package com.avangard.bratstvo.user.data.mapper

import com.avangard.bratstvo.user.data.model.InterestSimpleDto
import com.avangard.bratstvo.user.domain.mapper.InterestSimpleDtoMapper
import com.avangard.bratstvo.user.domain.model.InterestSimpleModel

class InterestSimpleDtoMapperImpl : InterestSimpleDtoMapper {

    override fun map(dto: InterestSimpleDto) = InterestSimpleModel(
        dto.title
    )
}