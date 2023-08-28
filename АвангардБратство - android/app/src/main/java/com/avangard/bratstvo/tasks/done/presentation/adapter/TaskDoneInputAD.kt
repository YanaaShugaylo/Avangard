package com.avangard.bratstvo.tasks.done.presentation.adapter

import androidx.core.widget.doAfterTextChanged
import com.avangard.bratstvo.databinding.TaskDoneInputtextItemBinding
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

internal fun taskDoneInputAD(
    inputText: (String) -> Unit
) = adapterDelegateViewBinding<TaskDoneItemModel.InputField, Any, TaskDoneInputtextItemBinding>(
    { inflater, parent ->
        TaskDoneInputtextItemBinding.inflate(inflater, parent, false)
    }
) {

    bind {
        binding.root.hint = getString(item.hintRes)
        binding.root.setText(item.text)

        binding.root.doAfterTextChanged {
            val comment = it.toString()
            item.text = comment
            inputText(comment)
        }
    }
}