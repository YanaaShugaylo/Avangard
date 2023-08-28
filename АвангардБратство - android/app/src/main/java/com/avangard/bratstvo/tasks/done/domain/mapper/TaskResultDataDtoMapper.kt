package com.avangard.bratstvo.tasks.done.domain.mapper

import com.avangard.bratstvo.tasks.done.data.model.TaskResultDataDto
import com.avangard.bratstvo.tasks.done.domain.model.TaskResultData

interface TaskResultDataDtoMapper {

    fun map(domainModel: TaskResultData): TaskResultDataDto
}