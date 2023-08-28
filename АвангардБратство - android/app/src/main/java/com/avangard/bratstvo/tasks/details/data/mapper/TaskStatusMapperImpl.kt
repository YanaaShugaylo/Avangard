package com.avangard.bratstvo.tasks.details.data.mapper

import com.avangard.bratstvo.tasks.details.domain.mapper.TaskStatusMapper
import com.avangard.bratstvo.tasks.details.presentation.model.TasksStatuses

class TaskStatusMapperImpl : TaskStatusMapper {

    override fun map(domainModel: String?) = when(domainModel) {
        "done" -> TasksStatuses.APPROVED
        "check" -> TasksStatuses.COMPLETED
        "activated" -> TasksStatuses.ACTIVATED
        else -> TasksStatuses.NOT_ACTIVATED
    }
}