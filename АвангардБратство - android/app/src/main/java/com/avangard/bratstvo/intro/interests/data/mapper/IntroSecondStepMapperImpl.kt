package com.avangard.bratstvo.intro.interests.data.mapper

import com.avangard.bratstvo.intro.interests.data.model.IntroSecondStepDto
import com.avangard.bratstvo.intro.interests.domain.mapper.IntroSecondStepMapper
import com.avangard.bratstvo.intro.interests.domain.model.IntroSecondStepData

class IntroSecondStepMapperImpl : IntroSecondStepMapper {

    override fun map(model: IntroSecondStepData) = IntroSecondStepDto(
        model.interestsIds
    )
}