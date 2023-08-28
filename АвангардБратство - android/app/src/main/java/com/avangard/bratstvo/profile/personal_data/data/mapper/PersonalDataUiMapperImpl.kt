package com.avangard.bratstvo.profile.personal_data.data.mapper

import com.avangard.bratstvo.profile.personal_data.domain.mapper.PersonalDataUiMapper
import com.avangard.bratstvo.profile.personal_data.domain.model.UserPersonalData
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import java.lang.StringBuilder

class PersonalDataUiMapperImpl : PersonalDataUiMapper {

    override fun map(domainModel: UserPersonalData): List<PersonalDataItemModel> {
        val hobbiesString = StringBuilder()

        for (i in domainModel.hobbies.indices) {
            if (i > 0) {
                hobbiesString.append(", ")
            }

            hobbiesString.append(domainModel.hobbies[i].title)
        }

        val items = listOf(
            PersonalDataItemModel.Photo(domainModel.photoUrl),
            PersonalDataItemModel.MainData(domainModel.fullName, domainModel.city),
            PersonalDataItemModel.ExtraData(domainModel.birthDate, domainModel.education),
            PersonalDataItemModel.Hobby(hobbiesString.toString()),
            PersonalDataItemModel.Interests(domainModel.interests),
            PersonalDataItemModel.ActionButton("Выйти из аккаунта")
        )

        return items
    }
}