package com.avangard.bratstvo.tasks.root.data.mapper

import com.avangard.bratstvo.tasks.root.data.model.TaskDto
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDetailsDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDtoMapper
import com.avangard.bratstvo.tasks.root.domain.model.Task

class TaskDtoMapperImpl(private val detailsMapper: TaskDetailsDtoMapper) : TaskDtoMapper {

    override fun map(dto: TaskDto) = Task(
        dto.id,
        dto.status,
        dto.statusText,
        detailsMapper.map(dto.taskDetails)
    )
}