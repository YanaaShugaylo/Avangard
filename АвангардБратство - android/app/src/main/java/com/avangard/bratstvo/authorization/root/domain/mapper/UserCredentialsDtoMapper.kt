package com.avangard.bratstvo.authorization.root.domain.mapper

import com.avangard.bratstvo.authorization.root.data.model.UserCredentialsDto
import com.avangard.bratstvo.authorization.root.domain.model.UserCredentials

interface UserCredentialsDtoMapper {

    fun map(model: UserCredentials): UserCredentialsDto
}