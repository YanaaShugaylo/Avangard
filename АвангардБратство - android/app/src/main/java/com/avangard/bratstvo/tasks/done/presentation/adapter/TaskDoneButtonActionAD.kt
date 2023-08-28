package com.avangard.bratstvo.tasks.done.presentation.adapter

import com.avangard.bratstvo.databinding.TaskDoneButtonItemBinding
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun taskDoneButtonActionAD(
    onItemClick: (TaskDoneItemModel) -> Unit
) = adapterDelegateViewBinding<TaskDoneItemModel.ButtonAction, Any, TaskDoneButtonItemBinding>(
    { inflater, parent ->
        TaskDoneButtonItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.root.text = getString(item.textRes)

        binding.root.isEnabled = item.isContentLoaded
    }
}