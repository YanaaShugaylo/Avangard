package com.avangard.bratstvo.tasks.details.domain.mapper

import com.avangard.bratstvo.tasks.details.presentation.model.TasksStatuses

interface TaskStatusMapper {
    fun map(domainModel: String?): TasksStatuses
}