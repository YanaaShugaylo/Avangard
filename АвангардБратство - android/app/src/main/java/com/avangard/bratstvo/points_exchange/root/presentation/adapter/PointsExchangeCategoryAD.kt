package com.avangard.bratstvo.points_exchange.root.presentation.adapter

import android.content.res.ColorStateList
import android.util.Log
import androidx.core.content.ContextCompat
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.PointsExchangeCategoryItemBinding
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizesCategoryUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun pointsExchangeCategoryAD(
    onItemClick: (Int) -> Unit
) = adapterDelegateViewBinding<PrizesCategoryUiModel, PrizesCategoryUiModel, PointsExchangeCategoryItemBinding>(
    { inflater, parent ->
        PointsExchangeCategoryItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(bindingAdapterPosition)
    }

    bind {
        binding.root.text = item.title

        binding.root.backgroundTintList = ColorStateList.valueOf(
            ContextCompat.getColor(
                binding.root.context, if (item.isChoosen) {
                    R.color.base_accent_color
                } else {
                    R.color.base_button_border_color
                }
            )
        )

        binding.root.setTextColor(
            ContextCompat.getColor(
                binding.root.context, if (item.isChoosen) {
                    R.color.base_accent_color
                } else {
                    R.color.base_button_text_color
                }
            )
        )
    }
}