package com.avangard.bratstvo.education.root.presentation.adapter

import com.avangard.bratstvo.databinding.EducationCommonItemBinding
import com.avangard.bratstvo.education.root.presentation.model.EducationItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun educationCommonItemAD(
    onItemClick: (Int, Boolean) -> Unit
) = adapterDelegateViewBinding<EducationItemUiModel.Common, Any, EducationCommonItemBinding>(
    { inflater, parent ->
        EducationCommonItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item.id, false)
    }

    bind {
        binding.educationCommonTitleTv.text = item.title
    }
}