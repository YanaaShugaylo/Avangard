package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import com.avangard.bratstvo.databinding.ProfilePersonalDataMainDataItemBinding
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun personalDataMainDataAD(
) = adapterDelegateViewBinding<PersonalDataItemModel.MainData, Any, ProfilePersonalDataMainDataItemBinding>(
    { inflater, parent ->
        ProfilePersonalDataMainDataItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.personalMainDataItemNameTv.text = item.fullName
        binding.personalMainDataItemCityTv.text = item.city
    }
}