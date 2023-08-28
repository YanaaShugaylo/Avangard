package com.avangard.bratstvo.intro.about_user.data.mapper

import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbiesListUiMapper
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbyUiMapper
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby

class HobbiesListUiMapperImpl(private val hobbyUiMapper: HobbyUiMapper) : HobbiesListUiMapper {

    override fun map(domainList: List<Hobby>) = domainList.map {
        hobbyUiMapper.map(it)
    }
}