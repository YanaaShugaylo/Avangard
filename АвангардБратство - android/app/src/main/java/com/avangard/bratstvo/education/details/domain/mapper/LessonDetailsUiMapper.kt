package com.avangard.bratstvo.education.details.domain.mapper

import com.avangard.bratstvo.education.details.presentation.model.LessonDetailsUiModel
import com.avangard.bratstvo.education.root.domain.model.Lesson

interface LessonDetailsUiMapper {

    fun map(domainModel: Lesson?): LessonDetailsUiModel?
}