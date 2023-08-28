package com.avangard.bratstvo.profile.personal_data.domain

import com.avangard.bratstvo.profile.personal_data.domain.model.UserPersonalData

interface PersonalDataRepository {

    suspend fun getData(): UserPersonalData
}