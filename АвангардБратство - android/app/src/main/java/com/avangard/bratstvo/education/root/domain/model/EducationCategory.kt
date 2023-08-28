package com.avangard.bratstvo.education.root.domain.model

import com.avangard.bratstvo.tasks.root.domain.model.ImageAttachment

class EducationCategory(
    val id: Int,
    val title: String,
    val attachment: ImageAttachment?
)