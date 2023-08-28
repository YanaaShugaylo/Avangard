package com.avangard.bratstvo.tasks.root.data.mapper

import com.avangard.bratstvo.tasks.root.data.model.TasksCategoryDto
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoryDtoMapper
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory

class TasksCategoryDtoMapperImpl(
    private val taskAttachmentDtoMapper: ImageAttachmentDtoMapper
) : TasksCategoryDtoMapper {

    override fun map(dto: TasksCategoryDto?) = if (dto == null) {
        null
    } else {
        TasksCategory(
            dto.id,
            dto.title,
            dto.tasksActiveCount ?: 0,
            dto.taskActiveAndCompletedCount ?: 0,
            taskAttachmentDtoMapper.map(dto.attachment)
        )
    }
}