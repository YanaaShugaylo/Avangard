package com.avangard.bratstvo.tasks.root.data.mapper

import com.avangard.bratstvo.tasks.root.data.model.ImageAttachmentDto
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper
import com.avangard.bratstvo.tasks.root.domain.model.ImageAttachment

class ImageAttachmentDtoMapperImpl : ImageAttachmentDtoMapper {

    override fun map(dto: ImageAttachmentDto?) = if (dto == null) {
        null
    } else {
        ImageAttachment(
            dto.uuid,
            dto.title,
            dto.fileName,
            dto.previewUrl,
            dto.originalUrl,
            dto.customProperties,
            dto.extension,
            dto.size
        )
    }
}