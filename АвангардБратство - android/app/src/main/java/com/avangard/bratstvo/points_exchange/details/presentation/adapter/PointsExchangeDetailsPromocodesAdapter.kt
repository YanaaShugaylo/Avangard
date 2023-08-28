package com.avangard.bratstvo.points_exchange.details.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PointsExchangeDetailsPromocodesAdapter(
    onItemClick: (Boolean, String) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(pointsExchangeDetailsPromocodeAD(onItemClick))
            .addDelegate(pointsExchangeDetailsQrCodeAD())
            .addDelegate(pointsExchangeDetailsInstructionAD())
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}