package com.avangard.bratstvo.tasks.root.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class TasksAdapter(
    onItemClick: (Int, Boolean) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(tasksCategoryAD(onItemClick))
            .addDelegate(tasksCommonItemAD(onItemClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}