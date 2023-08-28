package com.avangard.bratstvo.points_exchange.root.presentation.adapter

import android.content.res.ColorStateList
import android.util.Log
import android.view.View
import coil.load
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.PointsExchangePartnerItemBinding
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizeUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun pointsExchangePartnerAD(
    onItemClick: (Int) -> Unit
) = adapterDelegateViewBinding<PrizeUiModel, PrizeUiModel, PointsExchangePartnerItemBinding>(
    { inflater, parent ->
        PointsExchangePartnerItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item.id)
    }

    bind {
        binding.partnerItemIconIv.load(item.imageUrl)

        binding.partnerItemTitleTv.text = item.title
        binding.partnerItemPriceTv.text = binding.root.context.resources.getQuantityString(
            R.plurals.points_exchange_points_pattern,
            item.points, item.points
        )
        binding.partnerItemExtraInfoTv.visibility = when {
            item.multiplier != null -> {
                binding.partnerItemExtraInfoTv.setTextColor(getColor(R.color.points_exchange_term_text_multiplier))
                binding.partnerItemExtraInfoTv.backgroundTintList =
                    ColorStateList.valueOf(getColor(R.color.base_inputfield_background_color))
                binding.partnerItemExtraInfoTv.text =
                    getString(R.string.points_exchange_multiplier_pattern, item.multiplier!!)
                binding.partnerItemPriceTv.text = getString(R.string.points_exchange_bought_text)
                View.VISIBLE
            }
            item.endDate != null -> {
                binding.partnerItemExtraInfoTv.setTextColor(getColor(R.color.points_exchange_term_text_date))
                binding.partnerItemExtraInfoTv.backgroundTintList =
                    ColorStateList.valueOf(getColor(R.color.points_exchange_term_background_date))
                binding.partnerItemExtraInfoTv.text = getString(R.string.points_exchange_term_pattern, item.endDate!!)
                View.VISIBLE
            }
            else -> View.GONE
        }

        binding.partnerItemExtraInfoTv.invalidate()
    }
}