package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import com.avangard.bratstvo.databinding.ProfilePersonalDataActionButtonItemBinding
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun personalDataActionButtonAD(
    onClick: () -> Unit
) = adapterDelegateViewBinding<PersonalDataItemModel.ActionButton, Any, ProfilePersonalDataActionButtonItemBinding>(
    { inflater, parent ->
        ProfilePersonalDataActionButtonItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onClick()
    }

    bind {
        binding.root.text = item.text
    }
}