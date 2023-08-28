package com.avangard.bratstvo.tasks.root.data.model

import com.avangard.bratstvo.tasks.details.data.model.TaskDetailsDto
import com.google.gson.annotations.SerializedName

class TasksCategoryDto(
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("tasks_active_count")
    val tasksActiveCount: Int?,
    @SerializedName("task_active_and_completed_count")
    val taskActiveAndCompletedCount: Int?,
    val attachment: ImageAttachmentDto?,
    val tasks: List<TaskDetailsDto>
)