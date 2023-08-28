package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.ProfilePersonalDataExtraDataItemBinding
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun personalDataExtraDataAD(
) = adapterDelegateViewBinding<PersonalDataItemModel.ExtraData, Any, ProfilePersonalDataExtraDataItemBinding>(
    { inflater, parent ->
        ProfilePersonalDataExtraDataItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        val birthdayTitle = getString(R.string.profile_personal_data_birthday_title)
        val birthdayString = "$birthdayTitle ${item.birthday}"
        val birthdayTitleSpannable = SpannableString(birthdayString)
        birthdayTitleSpannable.setSpan(
            ForegroundColorSpan(getColor(R.color.base_text_color)),
            birthdayTitle.length,
            birthdayString.length,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )
        binding.personalExtraDataItemBirthdayTv.text = birthdayTitleSpannable

        val educationTitle = getString(R.string.profile_personal_data_education_title)
        val educationString = "$educationTitle ${item.education}"
        val educationTitleSpannable = SpannableString(educationString)
        educationTitleSpannable.setSpan(
            ForegroundColorSpan(getColor(R.color.base_text_color)),
            educationTitle.length,
            educationString.length,
            SpannableString.SPAN_INCLUSIVE_INCLUSIVE
        )

        binding.personalExtraDataItemEducationTv.text = educationTitleSpannable
    }
}