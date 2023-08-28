package com.avangard.bratstvo.profile.personal_data.domain.mapper

import com.avangard.bratstvo.profile.personal_data.data.model.UserPersonalDataDto
import com.avangard.bratstvo.profile.personal_data.domain.model.UserPersonalData

interface PersonalDataDtoMapper {

    fun map(dto: UserPersonalDataDto): UserPersonalData
}