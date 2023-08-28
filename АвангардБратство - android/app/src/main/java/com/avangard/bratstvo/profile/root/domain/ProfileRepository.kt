package com.avangard.bratstvo.profile.root.domain

import com.avangard.bratstvo.profile.root.domain.model.UserRating
import com.avangard.bratstvo.profile.root.domain.model.UserSkill
import com.avangard.bratstvo.profile.root.presentation.UserHistory

interface ProfileRepository {

    suspend fun getRating(): UserRating
    suspend fun getHistory(): UserHistory
    suspend fun getNewBalance(): UserNewBalance
    suspend fun getSkills(): List<UserSkill>

}