package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.ProfilePersonalDataHobbyItemBinding
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun personalDataHobbyAD(
) = adapterDelegateViewBinding<PersonalDataItemModel.Hobby, Any, ProfilePersonalDataHobbyItemBinding>(
    { inflater, parent ->
        ProfilePersonalDataHobbyItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        val hobbyTitle = getString(R.string.profile_personal_data_hobby_title)
        val hobbyString = "$hobbyTitle ${item.hobby}"
        val hobbyTitleSpannable = SpannableString(hobbyString)
        hobbyTitleSpannable.setSpan(
            ForegroundColorSpan(getColor(R.color.base_text_color)),
            hobbyTitle.length,
            hobbyString.length,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.personalHobbyDataItemTv.text = hobbyTitleSpannable
    }
}