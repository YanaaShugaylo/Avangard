package com.avangard.bratstvo.tasks.root.presentation.model

import com.avangard.bratstvo.tasks.details.presentation.model.TasksStatuses

open class TasksItemUiModel {

    class Category(
        val id: Int,
        val text: String,
        val percent: Int,
        val backgroundUrl: String?
    ) : TasksItemUiModel()

    class Common(
        val id: Int,
        val text: String,
        val points: Int,
        val status: TasksStatuses,
        val days: Int
    ) : TasksItemUiModel()
}