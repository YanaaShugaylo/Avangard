package com.avangard.bratstvo.points_exchange.details.presentation.adapter

import com.avangard.bratstvo.databinding.PointsExchangeDetailsInstructionItemBinding
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeRewardUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun pointsExchangeDetailsInstructionAD(
) = adapterDelegateViewBinding<PrizeRewardUiModel.Instruction, Any, PointsExchangeDetailsInstructionItemBinding>(
    { inflater, parent ->
        PointsExchangeDetailsInstructionItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.exchangeDetailsInstructionTitleTv.text = item.title
        binding.exchangeDetailsInstructionTv.text = item.description
    }
}