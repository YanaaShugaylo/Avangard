package com.avangard.bratstvo.tasks.root.data.mapper

import android.util.Log
import com.avangard.bratstvo.tasks.root.data.model.TaskDto
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksListDtoMapper

class TasksListDtoMapperImpl(private val taskDtoMapper: TaskDtoMapper) : TasksListDtoMapper {

    override fun map(dtoList: List<TaskDto>) = dtoList.map {
        taskDtoMapper.map(it)
    }
}