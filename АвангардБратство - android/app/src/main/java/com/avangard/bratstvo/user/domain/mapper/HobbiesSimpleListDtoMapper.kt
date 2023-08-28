package com.avangard.bratstvo.user.domain.mapper

import com.avangard.bratstvo.user.data.model.HobbySimpleDto
import com.avangard.bratstvo.user.domain.model.HobbySimpleModel

interface HobbiesSimpleListDtoMapper {

    fun map(dtoList: List<HobbySimpleDto>): List<HobbySimpleModel>
}