package com.avangard.bratstvo.tasks.root.data.model

import com.avangard.bratstvo.tasks.details.data.model.TaskDetailsDto
import com.google.gson.annotations.SerializedName

class TaskDto(
    val id: Int,
    @SerializedName("status_raw")
    val status: Int,
    @SerializedName("status")
    val statusText: String,
    @SerializedName("task")
    val taskDetails: TaskDetailsDto
)