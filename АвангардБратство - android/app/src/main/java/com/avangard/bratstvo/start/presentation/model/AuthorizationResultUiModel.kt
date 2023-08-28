package com.avangard.bratstvo.start.presentation.model

class AuthorizationResultUiModel(
    val isAuthSucceed: Boolean,
    val isFirstTime: Boolean,
    val errorMessage: String? = null
)