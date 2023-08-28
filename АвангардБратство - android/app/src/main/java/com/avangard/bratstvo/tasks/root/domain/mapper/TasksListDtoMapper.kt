package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.root.data.model.TaskDto
import com.avangard.bratstvo.tasks.root.domain.model.Task

interface TasksListDtoMapper {

    fun map(dtoList: List<TaskDto>): List<Task>
}