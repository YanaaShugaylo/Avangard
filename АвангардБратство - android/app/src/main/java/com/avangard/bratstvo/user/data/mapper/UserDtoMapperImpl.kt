package com.avangard.bratstvo.user.data.mapper

import com.avangard.bratstvo.base.extensions.domain.string.getDateFromUtc
import com.avangard.bratstvo.user.data.model.UserDto
import com.avangard.bratstvo.user.domain.mapper.HobbiesSimpleListDtoMapper
import com.avangard.bratstvo.user.domain.mapper.InterestsSimpleListDtoMapper
import com.avangard.bratstvo.user.domain.mapper.UserDtoMapper
import com.avangard.bratstvo.user.domain.model.User

class UserDtoMapperImpl(
    private val hobbiesSimpleListDtoMapper: HobbiesSimpleListDtoMapper,
    private val interestsSimpleListDtoMapper: InterestsSimpleListDtoMapper
) : UserDtoMapper {

    override fun map(dto: UserDto) = User(
        (dto.birthDate ?: "").getDateFromUtc(),
        dto.education ?: "",
        dto.about ?: "",
        dto.city ?: "",
        dto.fullName ?: "",
        dto.balance,
        dto.photoUrl ?: "",
        hobbiesSimpleListDtoMapper.map(dto.hobbies),
        interestsSimpleListDtoMapper.map(dto.interests)
    )
}