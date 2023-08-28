package com.avangard.bratstvo.authorization.root.domain.model

class AuthorizationResult(
    val isAuthSucceed: Boolean,
    val isFirstTime: Boolean,
    val errorMessage: String?
)