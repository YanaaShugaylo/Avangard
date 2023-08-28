package com.avangard.bratstvo.profile.personal_data.domain.mapper

import com.avangard.bratstvo.profile.personal_data.domain.model.UserPersonalData
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel

interface PersonalDataUiMapper {

    fun map(domainModel: UserPersonalData): List<PersonalDataItemModel>
}