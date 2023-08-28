package com.avangard.bratstvo.tasks.details.data.model

import com.avangard.bratstvo.tasks.root.data.model.ImageAttachmentDto
import com.avangard.bratstvo.tasks.root.data.model.TaskUserDataDto
import com.avangard.bratstvo.tasks.root.data.model.TasksCategoryDto
import com.google.gson.annotations.SerializedName

data class TaskDetailsDto(
    val id: Int,
    val type: String,
    val name: String,
    @SerializedName("description_link")
    val descriptionLink: String?,
    @SerializedName("reward")
    val pointsReward: Int,
    @SerializedName("end_date")
    val endDate: String?,
    val category: TasksCategoryDto?,
    val user: TaskUserDataDto?,
    val attachment: ImageAttachmentDto?
)