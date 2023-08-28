package com.avangard.bratstvo.intro.interests.data.mapper

import com.avangard.bratstvo.intro.interests.data.model.InterestsListDto
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestDtoMapper
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestsListDtoMapper
import com.avangard.bratstvo.intro.interests.domain.model.Interest

class InterestsListDtoMapperImpl(
    private val interestDtoMapper: InterestDtoMapper
) : InterestsListDtoMapper {

    override fun map(dtoList: InterestsListDto): List<Interest> = dtoList.interests.map {
        interestDtoMapper.map(it)
    }
}