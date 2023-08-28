package com.avangard.bratstvo.intro.interests.domain.mapper

import com.avangard.bratstvo.intro.interests.data.model.InterestDto
import com.avangard.bratstvo.intro.interests.domain.model.Interest

interface InterestDtoMapper {

    fun map(dto: InterestDto): Interest
}