package com.avangard.bratstvo.tests.root.presentation.adapter

import com.avangard.bratstvo.databinding.TestCommonItemBinding
import com.avangard.bratstvo.tests.root.presentation.model.TestItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun testCommonItemAD(
) = adapterDelegateViewBinding<TestItemUiModel.Answer, Any, TestCommonItemBinding>(
    { inflater, parent ->
        TestCommonItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.testAnswerSelectedRb.isSelected = item.selected
        binding.testAnswerTv.text = item.text
    }
}