package com.avangard.bratstvo.education.root.data.model

import com.avangard.bratstvo.tasks.root.data.model.ImageAttachmentDto
import com.google.gson.annotations.SerializedName

class EducationCategoryDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    val attachment: ImageAttachmentDto?
)