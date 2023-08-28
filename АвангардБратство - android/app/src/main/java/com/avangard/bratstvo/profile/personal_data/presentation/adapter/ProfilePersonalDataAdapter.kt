package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class ProfilePersonalDataAdapter(
    onActionButtonClick: () -> Unit
) : AsyncListDifferDelegationAdapter<Any>(DiffCallback) {

    init {
        delegatesManager
            .addDelegate(personalDataPhotoAD())
            .addDelegate(personalDataMainDataAD())
            .addDelegate(personalDataExtraDataAD())
            .addDelegate(personalDataHobbyAD())
            .addDelegate(personalDataInterestsAD())
            .addDelegate(personalDataActionButtonAD(onActionButtonClick))
    }

    private companion object DiffCallback : DiffUtil.ItemCallback<Any>() {

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = false

        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = false
    }
}