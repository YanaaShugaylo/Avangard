package com.avangard.bratstvo.education.root.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class EducationAdapter(
    onItemClick: (Int, Boolean) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(educationCategoryAD(onItemClick))
            .addDelegate(educationCommonItemAD(onItemClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}