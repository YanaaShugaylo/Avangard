package com.avangard.bratstvo.authorization.root.domain.mapper

import com.avangard.bratstvo.authorization.root.domain.model.AuthorizationResult
import com.avangard.bratstvo.start.presentation.model.AuthorizationResultUiModel

interface AuthorizationResultUiMapper {
    fun map(domainModel: AuthorizationResult): AuthorizationResultUiModel
}