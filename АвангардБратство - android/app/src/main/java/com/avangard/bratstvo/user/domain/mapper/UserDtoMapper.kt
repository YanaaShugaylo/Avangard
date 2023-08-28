package com.avangard.bratstvo.user.domain.mapper

import com.avangard.bratstvo.user.data.model.UserDto
import com.avangard.bratstvo.user.domain.model.User

interface UserDtoMapper {
    fun map(dto: UserDto): User
}