package com.avangard.bratstvo.intro.interests.domain.mapper

import com.avangard.bratstvo.intro.interests.data.model.InterestsListDto
import com.avangard.bratstvo.intro.interests.domain.model.Interest

interface InterestsListDtoMapper {

    fun map(dtoList: InterestsListDto): List<Interest>
}