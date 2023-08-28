package com.avangard.bratstvo.education.root.domain.mapper

import com.avangard.bratstvo.education.root.data.model.LessonDto
import com.avangard.bratstvo.education.root.domain.model.Lesson

interface LessonDtoMapper {

    fun map(dto: LessonDto): Lesson
}