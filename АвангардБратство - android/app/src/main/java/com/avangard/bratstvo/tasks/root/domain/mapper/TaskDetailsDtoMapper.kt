package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.details.data.model.TaskDetailsDto
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails

interface TaskDetailsDtoMapper {
    fun map(dto: TaskDetailsDto): TaskDetails
}