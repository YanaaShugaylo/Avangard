package com.avangard.bratstvo.authorization.root.data.mapper

import com.avangard.bratstvo.authorization.root.data.model.AuthUserDataDto
import com.avangard.bratstvo.authorization.root.domain.mapper.AuthorizationResultDtoMapper
import com.avangard.bratstvo.authorization.root.domain.model.AuthorizationResult

class AuthorizationResultDtoMapperImpl : AuthorizationResultDtoMapper {

    override fun map(dto: AuthUserDataDto?, errorMessage: String?) = if (dto == null) {
        AuthorizationResult(isAuthSucceed = false, isFirstTime = true, errorMessage)
    } else {
        AuthorizationResult(isAuthSucceed = true, isFirstTime = dto.isUserNew, errorMessage)
    }
}