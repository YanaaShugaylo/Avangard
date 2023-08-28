package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.ProfilePersonalDataInterestsItemBinding
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun personalDataInterestsAD(
) = adapterDelegateViewBinding<PersonalDataItemModel.Interests, Any, ProfilePersonalDataInterestsItemBinding>(
    { inflater, parent ->
        ProfilePersonalDataInterestsItemBinding.inflate(inflater, parent, false)
    }
) {

    val chipsAdapter = PersonalDataInterestsAdapter(binding.personalInterstsDataItemChips)

    bind {
        binding.personalInterstsDataItemTitleTv.text = getString(R.string.profile_personal_data_interests_title)

        chipsAdapter.items = item.interests
    }
}