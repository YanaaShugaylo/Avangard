package com.avangard.bratstvo.base.root.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class BaseViewModelDelegate(
    viewModel: ViewModel
) {

    val coroutineScope = viewModel.viewModelScope
}