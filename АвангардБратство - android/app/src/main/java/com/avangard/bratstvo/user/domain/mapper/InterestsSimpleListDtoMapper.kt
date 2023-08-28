package com.avangard.bratstvo.user.domain.mapper

import com.avangard.bratstvo.user.data.model.InterestSimpleDto
import com.avangard.bratstvo.user.domain.model.InterestSimpleModel

interface InterestsSimpleListDtoMapper {

    fun map(dtoList: List<InterestSimpleDto>): List<InterestSimpleModel>
}