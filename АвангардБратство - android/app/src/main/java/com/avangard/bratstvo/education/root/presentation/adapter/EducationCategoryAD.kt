package com.avangard.bratstvo.education.root.presentation.adapter

import coil.load
import com.avangard.bratstvo.databinding.EducationCategoryItemBinding
import com.avangard.bratstvo.education.root.presentation.model.EducationItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun educationCategoryAD(
    onItemClick: (Int, Boolean) -> Unit
) = adapterDelegateViewBinding<EducationItemUiModel.Category, Any, EducationCategoryItemBinding>(
    { inflater, parent ->
        EducationCategoryItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item.id, true)
    }

    bind {
        binding.educationCategoryBackgroundIv.load(item.backgroundUrl)
        binding.educationCategoryTitleTv.text = item.title
    }
}