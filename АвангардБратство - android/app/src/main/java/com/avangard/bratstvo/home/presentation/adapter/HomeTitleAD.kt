package com.avangard.bratstvo.home.presentation.adapter

import com.avangard.bratstvo.databinding.HomeTitleItemBinding
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun homeTitleAD(
) = adapterDelegateViewBinding<HomeItemModel.Title, Any, HomeTitleItemBinding>(
    { inflater, parent ->
        HomeTitleItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.root.text = item.text
    }
}