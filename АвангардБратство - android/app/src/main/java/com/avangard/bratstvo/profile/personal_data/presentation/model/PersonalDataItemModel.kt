package com.avangard.bratstvo.profile.personal_data.presentation.model

import com.avangard.bratstvo.user.domain.model.InterestSimpleModel

open class PersonalDataItemModel {

    internal class Photo(
        val imageUrl: String
    ) : PersonalDataItemModel()

    internal class MainData(
        val fullName: String,
        val city: String
    ) : PersonalDataItemModel()

    internal class ExtraData(
        val birthday: String,
        val education: String
    ) : PersonalDataItemModel()

    internal class Hobby(
        val hobby: String
    ) : PersonalDataItemModel()

    internal class Interests(
        val interests: List<InterestSimpleModel>
    ) : PersonalDataItemModel()

    internal class ActionButton(
        val text: String
    ) : PersonalDataItemModel()
}