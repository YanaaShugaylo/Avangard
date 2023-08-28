package com.avangard.bratstvo.intro.interests.data.mapper

import com.avangard.bratstvo.intro.interests.domain.mapper.InterestUiMapper
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestsListUiMapper
import com.avangard.bratstvo.intro.interests.domain.model.Interest

class InterestsListUiMapperImpl(private val interestUiMapper: InterestUiMapper) : InterestsListUiMapper {

    override fun map(domainList: List<Interest>) = domainList.map {
        interestUiMapper.map(it)
    }
}