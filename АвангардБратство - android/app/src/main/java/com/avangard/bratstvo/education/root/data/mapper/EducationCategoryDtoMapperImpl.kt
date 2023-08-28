package com.avangard.bratstvo.education.root.data.mapper

import com.avangard.bratstvo.education.root.data.model.EducationCategoryDto
import com.avangard.bratstvo.education.root.domain.mapper.EducationCategoryDtoMapper
import com.avangard.bratstvo.education.root.domain.model.EducationCategory
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper

class EducationCategoryDtoMapperImpl(
    private val imageAttachmentDtoMapper: ImageAttachmentDtoMapper
) : EducationCategoryDtoMapper {

    override fun map(dto: EducationCategoryDto) = EducationCategory(
        dto.id,
        dto.title,
        imageAttachmentDtoMapper.map(dto.attachment)
    )
}