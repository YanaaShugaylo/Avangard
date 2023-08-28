package com.avangard.bratstvo.intro.interests.domain.mapper

import com.avangard.bratstvo.intro.interests.data.model.IntroSecondStepDto
import com.avangard.bratstvo.intro.interests.domain.model.IntroSecondStepData

interface IntroSecondStepMapper {

    fun map(model: IntroSecondStepData): IntroSecondStepDto
}