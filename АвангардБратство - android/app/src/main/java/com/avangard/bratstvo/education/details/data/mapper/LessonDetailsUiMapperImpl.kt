package com.avangard.bratstvo.education.details.data.mapper

import com.avangard.bratstvo.education.details.domain.mapper.LessonDetailsUiMapper
import com.avangard.bratstvo.education.details.presentation.model.LessonDetailsUiModel
import com.avangard.bratstvo.education.root.domain.model.Lesson

class LessonDetailsUiMapperImpl : LessonDetailsUiMapper {

    override fun map(domainModel: Lesson?) = if (domainModel == null) {
        null
    } else {
        LessonDetailsUiModel(
            domainModel.id,
            domainModel.title,
            domainModel.backgroundImageLink?.originalUrl ?: "",
            domainModel.descriptionLink
        )
    }
}