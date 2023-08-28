package com.avangard.bratstvo.tasks.done.data.mapper

import com.avangard.bratstvo.tasks.done.data.model.TaskResultDataDto
import com.avangard.bratstvo.tasks.done.domain.mapper.TaskResultDataDtoMapper
import com.avangard.bratstvo.tasks.done.domain.model.TaskResultData

class TaskResultDataDtoMapperImpl : TaskResultDataDtoMapper {

    override fun map(domainModel: TaskResultData) = TaskResultDataDto(
        domainModel.fileUuid,
        domainModel.comment
    )
}