package com.avangard.bratstvo.tasks.root.domain.model

class ImageAttachment(
    val uuid: String,
    val title: String,
    val fileName: String,
    val previewUrl: String,
    val originalUrl: String,
    val customProperties: Array<Any?>,
    val extension: String,
    val size: Int
)