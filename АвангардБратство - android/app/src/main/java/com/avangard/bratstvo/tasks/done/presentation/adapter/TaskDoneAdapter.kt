package com.avangard.bratstvo.tasks.done.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class TaskDoneAdapter(
    onItemClick: (TaskDoneItemModel) -> Unit,
    inputText: (String) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(taskDoneTitleAD())
            .addDelegate(taskDoneInputAD(inputText))
            .addDelegate(taskDoneButtonFileAD(onItemClick))
            .addDelegate(taskDoneButtonActionAD(onItemClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}