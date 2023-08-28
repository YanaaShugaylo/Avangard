package com.avangard.bratstvo.tasks.root.domain.model

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails

class Task(
    val id: Int,
    val status: Int,
    val statusText: String,
    val taskDetails: TaskDetails
)