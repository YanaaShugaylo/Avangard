package com.avangard.bratstvo.authorization.root.data.mapper

import com.avangard.bratstvo.authorization.root.domain.mapper.AuthorizationResultUiMapper
import com.avangard.bratstvo.authorization.root.domain.model.AuthorizationResult
import com.avangard.bratstvo.start.presentation.model.AuthorizationResultUiModel

class AuthorizationResultUiMapperImpl : AuthorizationResultUiMapper {

    override fun map(domainModel: AuthorizationResult) = AuthorizationResultUiModel(
        domainModel.isAuthSucceed,
        domainModel.isFirstTime,
        domainModel.errorMessage
    )
}