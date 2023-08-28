package com.avangard.bratstvo.tasks.details.data.mapper

import com.avangard.bratstvo.tasks.root.data.model.TaskUserDataDto
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskUserDataDtoMapper
import com.avangard.bratstvo.tasks.root.domain.model.TaskUserData

class TaskUserDataDtoMapperImpl : TaskUserDataDtoMapper {

    override fun map(dto: TaskUserDataDto?) = if (dto == null) {
        null
    } else {
        TaskUserData(
            dto.id,
            dto.status,
            dto.statusText
        )
    }
}