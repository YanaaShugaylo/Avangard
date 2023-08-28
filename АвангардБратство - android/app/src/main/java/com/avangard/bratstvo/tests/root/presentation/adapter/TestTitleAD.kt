package com.avangard.bratstvo.tests.root.presentation.adapter

import com.avangard.bratstvo.databinding.TestTitleItemBinding
import com.avangard.bratstvo.tests.root.presentation.model.TestItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun testTitleAD(
) = adapterDelegateViewBinding<TestItemUiModel.Title, Any, TestTitleItemBinding>(
    { inflater, parent ->
        TestTitleItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.root.text = item.title
    }
}