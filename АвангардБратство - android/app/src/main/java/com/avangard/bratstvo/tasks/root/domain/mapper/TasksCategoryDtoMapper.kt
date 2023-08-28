package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.root.data.model.TasksCategoryDto
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory

interface TasksCategoryDtoMapper {

    fun map(dto: TasksCategoryDto?): TasksCategory?
}