package com.avangard.bratstvo.tasks.root.presentation.adapter

import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.TasksCommonItemBinding
import com.avangard.bratstvo.tasks.root.presentation.model.TasksItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun tasksCommonItemAD(
    onItemClick: (Int, Boolean) -> Unit
) = adapterDelegateViewBinding<TasksItemUiModel.Common, Any, TasksCommonItemBinding>(
    { inflater, parent ->
        TasksCommonItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item.id, false)
    }

    bind {
        binding.taskTitleTv.text = item.text

        val resources = binding.root.resources

        binding.taskPointsTv.text =
            resources.getQuantityString(R.plurals.tasks_common_points_pattern, item.points, item.points)
        binding.taskTermTv.text =
            resources.getQuantityString(R.plurals.tasks_common_days_pattern, item.days, item.days)
    }
}