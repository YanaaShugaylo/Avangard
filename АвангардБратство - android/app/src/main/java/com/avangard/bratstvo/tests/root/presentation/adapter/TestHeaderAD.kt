package com.avangard.bratstvo.tests.root.presentation.adapter

import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.TestHeaderItemBinding
import com.avangard.bratstvo.tests.root.presentation.model.TestItemUiModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun testHeaderAD(
) = adapterDelegateViewBinding<TestItemUiModel.Header, Any, TestHeaderItemBinding>(
    { inflater, parent ->
        TestHeaderItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.testQuestionNumberTv.text = getString(R.string.test_question_number_pattern, item.questionNumber, 20)
        binding.testTimeTv.text = item.time
    }
}