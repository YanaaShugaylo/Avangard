package com.avangard.bratstvo.intro.about_user.domain.mapper

import com.avangard.bratstvo.intro.about_user.data.model.IntroFirstStepDto
import com.avangard.bratstvo.intro.about_user.domain.model.IntroFirstStepData

interface IntroFirstStepMapper {

    fun map(model: IntroFirstStepData): IntroFirstStepDto
}