package com.avangard.bratstvo.tasks.root.data.model

import com.google.gson.annotations.SerializedName

class TaskUserDataDto(
    val id: Int,
    @SerializedName("status_raw")
    val status: Int,
    @SerializedName("status")
    val statusText: String
)