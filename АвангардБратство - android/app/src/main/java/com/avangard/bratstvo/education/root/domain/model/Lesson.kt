package com.avangard.bratstvo.education.root.domain.model

import com.avangard.bratstvo.tasks.root.domain.model.ImageAttachment

class Lesson(
    val id: Int,
    val title: String,
    val backgroundImageLink: ImageAttachment?,
    val descriptionLink: String,
    val userRead: Boolean?
)