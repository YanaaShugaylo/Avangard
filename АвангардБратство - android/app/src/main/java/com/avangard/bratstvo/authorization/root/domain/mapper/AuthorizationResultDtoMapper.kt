package com.avangard.bratstvo.authorization.root.domain.mapper

import com.avangard.bratstvo.authorization.root.data.model.AuthUserDataDto
import com.avangard.bratstvo.authorization.root.domain.model.AuthorizationResult

interface AuthorizationResultDtoMapper {
    fun map(dto: AuthUserDataDto?, errorMessage: String?): AuthorizationResult
}