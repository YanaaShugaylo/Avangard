package com.avangard.bratstvo.tasks.details.domain.mapper

import com.avangard.bratstvo.tasks.details.presentation.model.TasksTypes

interface TaskTypeMapper {

    fun map(domainModel: String): TasksTypes
}