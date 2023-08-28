package com.avangard.bratstvo.tasks.root.domain.model

class TasksCategory(
    val id: Int,
    val title: String,
    val tasksActiveCount: Int,
    val taskActiveAndCompletedCount: Int,
    val attachment: ImageAttachment?
) {
    var tasks: List<Task>? = null
}