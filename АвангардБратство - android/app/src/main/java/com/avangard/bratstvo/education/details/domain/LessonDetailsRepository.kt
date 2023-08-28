package com.avangard.bratstvo.education.details.domain

import com.avangard.bratstvo.education.root.domain.model.Lesson

interface LessonDetailsRepository {

    suspend fun getLessonDetails(lessonId: Int): Lesson?
}