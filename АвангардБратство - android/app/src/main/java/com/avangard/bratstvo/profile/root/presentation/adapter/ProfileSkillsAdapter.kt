package com.avangard.bratstvo.profile.root.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ProfileSkillsAdapter(
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(skillAD())
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}