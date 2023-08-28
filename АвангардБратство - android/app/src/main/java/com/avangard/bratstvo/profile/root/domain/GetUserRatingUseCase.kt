package com.avangard.bratstvo.profile.root.domain

import com.avangard.bratstvo.profile.root.domain.model.UserRating

class GetUserRatingUseCase(private val profileRepository: ProfileRepository) {

    suspend operator fun invoke(): UserRating = profileRepository.getRating()
}