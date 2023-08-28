package com.avangard.bratstvo.tasks.details.domain.mapper

import com.avangard.bratstvo.tasks.root.data.model.TaskUserDataDto
import com.avangard.bratstvo.tasks.root.domain.model.TaskUserData

interface TaskUserDataDtoMapper {

    fun map(dto: TaskUserDataDto?): TaskUserData?
}