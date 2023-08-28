package com.avangard.bratstvo.user.data.mapper

import com.avangard.bratstvo.user.data.model.InterestSimpleDto
import com.avangard.bratstvo.user.domain.mapper.InterestSimpleDtoMapper
import com.avangard.bratstvo.user.domain.mapper.InterestsSimpleListDtoMapper

class InterestsSimpleListDtoMapperImpl(
    private val interestSimpleDtoMapper: InterestSimpleDtoMapper
) : InterestsSimpleListDtoMapper {

    override fun map(dtoList: List<InterestSimpleDto>) = dtoList.map {
        interestSimpleDtoMapper.map(it)
    }
}