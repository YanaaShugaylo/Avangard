package com.avangard.bratstvo.tasks.done.data.model

import com.google.gson.annotations.SerializedName

class TaskResultDataDto(
    @SerializedName("file_uuid")
    val fileUuid: String,
    val comment: String
)