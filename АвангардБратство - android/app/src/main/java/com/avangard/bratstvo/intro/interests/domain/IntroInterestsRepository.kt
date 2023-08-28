package com.avangard.bratstvo.intro.interests.domain

import com.avangard.bratstvo.intro.interests.domain.model.Interest
import com.avangard.bratstvo.intro.interests.domain.model.IntroSecondStepData

interface IntroInterestsRepository {

    suspend fun getInterests(): List<Interest>
    suspend fun saveInterests(data: IntroSecondStepData): Boolean
}