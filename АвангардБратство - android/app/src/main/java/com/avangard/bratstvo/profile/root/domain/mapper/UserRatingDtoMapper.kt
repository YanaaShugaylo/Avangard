package com.avangard.bratstvo.profile.root.domain.mapper

import com.avangard.bratstvo.profile.root.data.model.UserRatingDto
import com.avangard.bratstvo.profile.root.domain.model.UserRating

interface UserRatingDtoMapper {

    fun map(dto: UserRatingDto): UserRating
}