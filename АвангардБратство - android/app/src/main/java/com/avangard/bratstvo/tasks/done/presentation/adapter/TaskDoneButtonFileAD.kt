package com.avangard.bratstvo.tasks.done.presentation.adapter

import com.avangard.bratstvo.databinding.TaskDoneButtonWithimageItemBinding
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun taskDoneButtonFileAD(
    onItemClick: (TaskDoneItemModel) -> Unit
) = adapterDelegateViewBinding<TaskDoneItemModel.ButtonFile, Any, TaskDoneButtonWithimageItemBinding>(
    { inflater, parent ->
        TaskDoneButtonWithimageItemBinding.inflate(inflater, parent, false)
    }
) {

    binding.root.setOnClickListener {
        onItemClick(item)
    }

    bind {
        binding.taskDoneItemTv.text = item.text
    }
}