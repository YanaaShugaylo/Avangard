package com.avangard.bratstvo.education.root.data.mapper

import com.avangard.bratstvo.education.root.data.model.LessonDto
import com.avangard.bratstvo.education.root.domain.mapper.LessonDtoMapper
import com.avangard.bratstvo.education.root.domain.model.Lesson
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper

class LessonDtoMapperImpl(private val attachmentDtoMapper: ImageAttachmentDtoMapper) : LessonDtoMapper {

    override fun map(dto: LessonDto) = Lesson(
        dto.id,
        dto.title,
        attachmentDtoMapper.map(dto.backgroundLink),
        dto.descriptionLink ?: "",
        dto.userRead
    )
}