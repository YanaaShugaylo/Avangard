package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import coil.load
import coil.transform.CircleCropTransformation
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.ProfilePersonalDataPhotoItemBinding
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun personalDataPhotoAD(
) = adapterDelegateViewBinding<PersonalDataItemModel.Photo, Any, ProfilePersonalDataPhotoItemBinding>(
    { inflater, parent ->
        ProfilePersonalDataPhotoItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        if (item.imageUrl.isNotEmpty()) {
            binding.photoIv.load(item.imageUrl)
        } else {
            binding.photoIv.load(R.drawable.profile_placeholder_icon)
        }
    }
}