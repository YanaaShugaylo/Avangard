package com.avangard.bratstvo.tasks.details.domain.mapper

import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.details.presentation.model.TaskDetailsUiModel

interface TaskDetailsUiMapper {
    fun map(domainModel: TaskDetails?): TaskDetailsUiModel?
}