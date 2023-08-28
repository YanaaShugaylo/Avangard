package com.avangard.bratstvo.intro.about_user.data.mapper

import com.avangard.bratstvo.intro.about_user.data.model.IntroFirstStepDto
import com.avangard.bratstvo.intro.about_user.domain.mapper.IntroFirstStepMapper
import com.avangard.bratstvo.intro.about_user.domain.model.IntroFirstStepData

class IntroFirstStepMapperImpl : IntroFirstStepMapper {

    override fun map(model: IntroFirstStepData) = IntroFirstStepDto(
        model.about,
        model.hobbiesIds
    )
}