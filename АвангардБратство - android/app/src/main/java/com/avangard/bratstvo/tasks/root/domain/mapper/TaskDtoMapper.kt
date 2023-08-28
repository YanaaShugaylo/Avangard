package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.root.data.model.TaskDto
import com.avangard.bratstvo.tasks.root.domain.model.Task

interface TaskDtoMapper {

    fun map(dto: TaskDto): Task
}