package com.avangard.bratstvo.intro

import com.avangard.bratstvo.intro.about_user.introAboutUserModule
import com.avangard.bratstvo.intro.interests.introInterestsModule

fun introModules() = listOf(
    introAboutUserModule(),
    introInterestsModule()
)