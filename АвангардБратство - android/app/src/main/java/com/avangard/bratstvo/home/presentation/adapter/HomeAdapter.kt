package com.avangard.bratstvo.home.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class HomeAdapter(
    onItemClick: (HomeItemModel) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(homeTitleAD())
            .addDelegate(homeTaskAD(onItemClick))
            .addDelegate(homePointsAD(onItemClick))
            .addDelegate(homeCardWithBackgroundAD(onItemClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}