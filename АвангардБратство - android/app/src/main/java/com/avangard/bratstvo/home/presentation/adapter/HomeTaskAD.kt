package com.avangard.bratstvo.home.presentation.adapter

import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.HomeTaskItemBinding
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun homeTaskAD(
    onItemClick: (HomeItemModel) -> Unit
) = adapterDelegateViewBinding<HomeItemModel.Task, Any, HomeTaskItemBinding>(
    { inflater, parent ->
        HomeTaskItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.taskItemTitleTv.text = item.title
        binding.taskItemPointsTv.text = getString(R.string.home_task_points_text, item.points)
        binding.taskItemCategoryTv.text = item.categoryTitle
    }
}