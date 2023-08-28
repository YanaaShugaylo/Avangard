package com.avangard.bratstvo.tasks.root.data.mapper

import com.avangard.bratstvo.tasks.root.data.model.TasksCategoryDto
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoryDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoriesListDtoMapper

class TasksCategoriesListDtoMapperImpl(
    private val tasksCategoryDtoMapper: TasksCategoryDtoMapper
) : TasksCategoriesListDtoMapper {

    override fun map(dtoList: List<TasksCategoryDto>) = dtoList.mapNotNull {
        tasksCategoryDtoMapper.map(it)
    }
}