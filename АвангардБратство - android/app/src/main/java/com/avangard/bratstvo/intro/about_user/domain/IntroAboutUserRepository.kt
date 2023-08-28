package com.avangard.bratstvo.intro.about_user.domain

import com.avangard.bratstvo.intro.about_user.domain.model.Hobby
import com.avangard.bratstvo.intro.about_user.domain.model.IntroFirstStepData

interface IntroAboutUserRepository {

    suspend fun getHobbies(): List<Hobby>
    suspend fun saveHobbies(data: IntroFirstStepData): Boolean
}