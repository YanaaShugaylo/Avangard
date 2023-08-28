package com.avangard.bratstvo.tests.root.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class TestAdapter : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(testHeaderAD())
            .addDelegate(testTitleAD())
            .addDelegate(testCommonItemAD())
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}