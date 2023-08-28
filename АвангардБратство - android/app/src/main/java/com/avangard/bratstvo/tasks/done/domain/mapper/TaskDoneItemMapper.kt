package com.avangard.bratstvo.tasks.done.domain.mapper

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel

interface TaskDoneItemMapper {
    fun map(domainModel: TaskDetails?): List<TaskDoneItemModel>?
}