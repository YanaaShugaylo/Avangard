package com.avangard.bratstvo.intro.interests.domain.mapper

import com.avangard.bratstvo.intro.interests.domain.model.Interest
import com.avangard.bratstvo.intro.interests.presentation.model.InterestUiModel

interface InterestsListUiMapper {

    fun map(domainList: List<Interest>): List<InterestUiModel>
}