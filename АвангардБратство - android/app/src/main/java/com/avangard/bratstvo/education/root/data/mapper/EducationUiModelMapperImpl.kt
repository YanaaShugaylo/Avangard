package com.avangard.bratstvo.education.root.data.mapper

import com.avangard.bratstvo.education.root.domain.mapper.EducationUiModelMapper
import com.avangard.bratstvo.education.root.domain.model.EducationCategory
import com.avangard.bratstvo.education.root.domain.model.Lesson
import com.avangard.bratstvo.education.root.presentation.model.EducationItemUiModel
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper

class EducationUiModelMapperImpl() : EducationUiModelMapper {

    override fun map(domainModel: Any) = when(domainModel) {
        is EducationCategory -> {
            EducationItemUiModel.Category(
                domainModel.id,
                domainModel.title,
                domainModel.attachment?.originalUrl
            )
        }
        is Lesson -> EducationItemUiModel.Common(domainModel.id, domainModel.title)
        else -> null
    }
}