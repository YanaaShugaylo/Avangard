package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.root.data.model.TasksCategoryDto
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory

interface TasksCategoriesListDtoMapper {

    fun map(dtoList: List<TasksCategoryDto>): List<TasksCategory>
}