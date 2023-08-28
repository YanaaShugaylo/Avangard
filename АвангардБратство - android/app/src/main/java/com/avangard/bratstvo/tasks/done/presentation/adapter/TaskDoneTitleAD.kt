package com.avangard.bratstvo.tasks.done.presentation.adapter

import com.avangard.bratstvo.databinding.TaskDoneTitleItemBinding
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun taskDoneTitleAD() = adapterDelegateViewBinding<TaskDoneItemModel.Title, Any, TaskDoneTitleItemBinding>(
    { inflater, parent ->
        TaskDoneTitleItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.root.text = getString(item.textRes)
    }
}