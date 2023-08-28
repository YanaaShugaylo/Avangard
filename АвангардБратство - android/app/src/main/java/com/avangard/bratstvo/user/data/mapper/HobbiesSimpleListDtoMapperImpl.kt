package com.avangard.bratstvo.user.data.mapper

import com.avangard.bratstvo.user.data.model.HobbySimpleDto
import com.avangard.bratstvo.user.domain.mapper.HobbiesSimpleListDtoMapper
import com.avangard.bratstvo.user.domain.mapper.HobbySimpleDtoMapper

class HobbiesSimpleListDtoMapperImpl(
    private val hobbySimpleDtoMapper: HobbySimpleDtoMapper
) : HobbiesSimpleListDtoMapper {

    override fun map(dtoList: List<HobbySimpleDto>) = dtoList.map {
        hobbySimpleDtoMapper.map(it)
    }
}