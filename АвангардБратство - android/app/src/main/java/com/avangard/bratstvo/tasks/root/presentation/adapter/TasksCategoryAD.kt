package com.avangard.bratstvo.tasks.root.presentation.adapter

import coil.load
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.TasksCategoryItemBinding
import com.avangard.bratstvo.tasks.root.presentation.model.TasksItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun tasksCategoryAD(
    onItemClick: (Int, Boolean) -> Unit
) = adapterDelegateViewBinding<TasksItemUiModel.Category, Any, TasksCategoryItemBinding>(
    { inflater, parent ->
        TasksCategoryItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item.id, true)
    }

    bind {
        binding.taskCategoryBackgroundIv.load(item.backgroundUrl)
        binding.taskCategoryTitleTv.text = item.text

        binding.taskCategoryPi.progress = item.percent

        binding.taskCategoryDonePercentTv.text = getString(R.string.tasks_category_percent_pattern, item.percent)
    }
}