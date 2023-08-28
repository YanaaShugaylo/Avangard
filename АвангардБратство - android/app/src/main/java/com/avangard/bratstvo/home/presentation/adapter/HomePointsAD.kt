package com.avangard.bratstvo.home.presentation.adapter

import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.HomePointsItemBinding
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun homePointsAD(
    onItemClick: (HomeItemModel) -> Unit
) = adapterDelegateViewBinding<HomeItemModel.Points, Any, HomePointsItemBinding>(
    { inflater, parent ->
        HomePointsItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.pointsCountTv.text = getString(R.string.home_points_pattern, item.count)

    }
}