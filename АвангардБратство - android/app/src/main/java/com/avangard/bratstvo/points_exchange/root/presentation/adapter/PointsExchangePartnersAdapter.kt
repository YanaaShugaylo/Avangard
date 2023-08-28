package com.avangard.bratstvo.points_exchange.root.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizeUiModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PointsExchangePartnersAdapter(
    onItemClick: (Int) -> Unit
) : AsyncListDifferDelegationAdapter<PrizeUiModel>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(pointsExchangePartnerAD(onItemClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<PrizeUiModel>() {

        override fun areContentsTheSame(oldItem: PrizeUiModel, newItem: PrizeUiModel): Boolean = false

        override fun areItemsTheSame(
            oldItem: PrizeUiModel, newItem: PrizeUiModel
        ): Boolean = oldItem.id == newItem.id
    }
}