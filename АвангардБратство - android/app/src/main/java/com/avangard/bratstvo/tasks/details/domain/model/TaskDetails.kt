package com.avangard.bratstvo.tasks.details.domain.model

import com.avangard.bratstvo.tasks.root.domain.model.ImageAttachment
import com.avangard.bratstvo.tasks.root.domain.model.TaskUserData
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory

class TaskDetails(
    val id: Int,
    val type: String,
    val title: String,
    val endDate: String,
    val descriptionLink: String,
    val pointsReward: Int,
    val category: TasksCategory?,
    val taskUserData: TaskUserData?,
    val attachment: ImageAttachment?
)