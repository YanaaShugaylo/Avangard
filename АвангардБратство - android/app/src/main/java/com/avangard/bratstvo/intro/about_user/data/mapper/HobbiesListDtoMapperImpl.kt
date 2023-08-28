package com.avangard.bratstvo.intro.about_user.data.mapper

import com.avangard.bratstvo.intro.about_user.data.model.HobbiesListDto
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbiesListDtoMapper
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbyDtoMapper
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby

class HobbiesListDtoMapperImpl(
    private val hobbyDtoMapper: HobbyDtoMapper
) : HobbiesListDtoMapper {

    override fun map(dtoList: HobbiesListDto): List<Hobby> = dtoList.hobbies.map {
        hobbyDtoMapper.map(it)
    }
}