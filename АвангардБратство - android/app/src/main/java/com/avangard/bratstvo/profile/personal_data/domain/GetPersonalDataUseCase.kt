package com.avangard.bratstvo.profile.personal_data.domain

import com.avangard.bratstvo.profile.personal_data.domain.model.UserPersonalData

class GetPersonalDataUseCase(private val userPersonalDataRepository: PersonalDataRepository) {

    suspend operator fun invoke(): UserPersonalData = userPersonalDataRepository.getData()
}