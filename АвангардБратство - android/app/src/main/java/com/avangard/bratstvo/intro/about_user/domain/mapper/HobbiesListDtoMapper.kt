package com.avangard.bratstvo.intro.about_user.domain.mapper

import com.avangard.bratstvo.intro.about_user.data.model.HobbiesListDto
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby

interface HobbiesListDtoMapper {

    fun map(dtoList: HobbiesListDto): List<Hobby>
}