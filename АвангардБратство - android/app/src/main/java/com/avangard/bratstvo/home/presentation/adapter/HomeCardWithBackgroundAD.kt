package com.avangard.bratstvo.home.presentation.adapter

import androidx.core.view.isVisible
import coil.load
import com.avangard.bratstvo.databinding.HomeCardWithBackgroundItemBinding
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun homeCardWithBackgroundAD(
    onItemClick: (HomeItemModel) -> Unit
) = adapterDelegateViewBinding<HomeItemModel.CardWithBackground, Any, HomeCardWithBackgroundItemBinding>(
    { inflater, parent ->
        HomeCardWithBackgroundItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.cardItemTitleTv.text = item.text

        binding.cardItemDateTv.isVisible = item.date != null
        binding.cardItemDateTv.text = item.date

        binding.cardItemIconIv.isVisible = item.iconUrl != null
        binding.cardItemIconIv.load(item.iconUrl)

        binding.cardItemButtonTv.isVisible = item.buttonText != null
        binding.cardItemButtonIv.isVisible = item.buttonText != null
        binding.cardItemButtonTv.text = item.buttonText
    }
}