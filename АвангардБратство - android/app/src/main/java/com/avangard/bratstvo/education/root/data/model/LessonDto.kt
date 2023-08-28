package com.avangard.bratstvo.education.root.data.model

import com.avangard.bratstvo.tasks.root.data.model.ImageAttachmentDto
import com.google.gson.annotations.SerializedName

class LessonDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("attachment")
    val backgroundLink: ImageAttachmentDto,
    @SerializedName("description_link")
    val descriptionLink: String?,
    @SerializedName("user_read")
    val userRead: Boolean?
)