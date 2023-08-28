package com.avangard.bratstvo.education.root.domain

import com.avangard.bratstvo.education.root.domain.model.EducationCategory

class GetEducationCategoriesUseCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke(): List<EducationCategory> = educationRepository.getCategories()
}