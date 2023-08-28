package com.avangard.bratstvo.education.root.domain.mapper

import com.avangard.bratstvo.education.root.data.model.LessonsListDto
import com.avangard.bratstvo.education.root.domain.model.Lesson

interface LessonsListDtoMapper {

    fun map(dto: LessonsListDto): List<Lesson>
}