package com.avangard.bratstvo.education.details.domain

import com.avangard.bratstvo.education.root.domain.model.Lesson

class GetLessonDetailsUseCase(private val lessonDetailsRepository: LessonDetailsRepository) {

    suspend operator fun invoke(lessonId: Int): Lesson? = lessonDetailsRepository.getLessonDetails(lessonId)
}