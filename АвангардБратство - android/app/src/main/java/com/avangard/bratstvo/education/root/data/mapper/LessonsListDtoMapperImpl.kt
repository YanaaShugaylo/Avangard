package com.avangard.bratstvo.education.root.data.mapper

import com.avangard.bratstvo.education.root.data.model.LessonsListDto
import com.avangard.bratstvo.education.root.domain.mapper.LessonDtoMapper
import com.avangard.bratstvo.education.root.domain.mapper.LessonsListDtoMapper

class LessonsListDtoMapperImpl(private val lessonDtoMapper: LessonDtoMapper) : LessonsListDtoMapper {

    override fun map(dto: LessonsListDto) = dto.lessons.map {
        lessonDtoMapper.map(it)
    }
}