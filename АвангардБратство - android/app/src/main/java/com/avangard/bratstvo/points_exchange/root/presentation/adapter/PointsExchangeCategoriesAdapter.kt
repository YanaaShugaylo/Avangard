package com.avangard.bratstvo.points_exchange.root.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizesCategoryUiModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class PointsExchangeCategoriesAdapter(
    onItemClick: (Int) -> Unit
) : AsyncListDifferDelegationAdapter<PrizesCategoryUiModel>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(pointsExchangeCategoryAD(onItemClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<PrizesCategoryUiModel>() {

        override fun areItemsTheSame(oldItem: PrizesCategoryUiModel, newItem: PrizesCategoryUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PrizesCategoryUiModel, newItem: PrizesCategoryUiModel): Boolean {
            return oldItem.isChoosen == newItem.isChoosen
        }
    }
}