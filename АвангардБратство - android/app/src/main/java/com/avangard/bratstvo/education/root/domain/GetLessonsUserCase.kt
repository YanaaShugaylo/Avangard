package com.avangard.bratstvo.education.root.domain

import com.avangard.bratstvo.education.root.domain.model.Lesson

class GetLessonsUserCase(private val educationRepository: EducationRepository) {

    suspend operator fun invoke(categoryId: Int): List<Lesson> = educationRepository.getLessons(categoryId)
}