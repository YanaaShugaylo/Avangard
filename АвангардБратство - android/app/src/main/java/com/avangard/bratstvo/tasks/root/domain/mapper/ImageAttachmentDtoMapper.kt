package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.root.data.model.ImageAttachmentDto
import com.avangard.bratstvo.tasks.root.domain.model.ImageAttachment

interface ImageAttachmentDtoMapper {
    fun map(dto: ImageAttachmentDto?): ImageAttachment?
}