package com.avangard.bratstvo.profile.root.presentation.adapter

import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.ProfileSkillItemBinding
import com.avangard.bratstvo.profile.root.presentation.model.UserSkillUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun skillAD(
) = adapterDelegateViewBinding<UserSkillUiModel, Any, ProfileSkillItemBinding>(
    { inflater, parent ->
        ProfileSkillItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.profileSkillItemTitleTv.text = item.title
        binding.profileSkillItemCountPi.max = item.max
        binding.profileSkillItemCountPi.progress = item.count
        binding.profileSkillItemCountTv.text = getString(R.string.profile_skill_count_pattern, item.count, item.max)
    }
}