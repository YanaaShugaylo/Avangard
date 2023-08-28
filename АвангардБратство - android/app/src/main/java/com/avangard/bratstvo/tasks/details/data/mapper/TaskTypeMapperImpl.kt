package com.avangard.bratstvo.tasks.details.data.mapper

import com.avangard.bratstvo.tasks.details.domain.mapper.TaskTypeMapper
import com.avangard.bratstvo.tasks.details.presentation.model.TasksTypes

class TaskTypeMapperImpl : TaskTypeMapper {

    override fun map(domainModel: String) = when(domainModel) {
        "comment" -> TasksTypes.COMMENT
        "file" -> TasksTypes.FILE
        else -> TasksTypes.FILE_AND_COMMENT
    }
}