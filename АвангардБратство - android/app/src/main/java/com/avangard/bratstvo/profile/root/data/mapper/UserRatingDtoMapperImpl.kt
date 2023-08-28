package com.avangard.bratstvo.profile.root.data.mapper

import com.avangard.bratstvo.profile.root.data.model.UserRatingDto
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingDtoMapper
import com.avangard.bratstvo.profile.root.domain.model.UserRating

class UserRatingDtoMapperImpl : UserRatingDtoMapper {

    override fun map(dto: UserRatingDto) = UserRating(dto.total, dto.position)
}