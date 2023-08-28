package com.avangard.bratstvo.authorization.root.data.mapper

import com.avangard.bratstvo.authorization.root.data.model.UserCredentialsDto
import com.avangard.bratstvo.authorization.root.domain.mapper.UserCredentialsDtoMapper
import com.avangard.bratstvo.authorization.root.domain.model.UserCredentials

class UserCredentialsDtoMapperImpl : UserCredentialsDtoMapper {

    override fun map(model: UserCredentials) = UserCredentialsDto(
        model.login,
        model.password
    )
}