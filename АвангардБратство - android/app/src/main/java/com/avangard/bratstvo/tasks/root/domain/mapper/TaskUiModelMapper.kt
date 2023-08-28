package com.avangard.bratstvo.tasks.root.domain.mapper

import com.avangard.bratstvo.tasks.root.presentation.model.TasksItemUiModel

interface TaskUiModelMapper {
    fun map(domainModel: Any): TasksItemUiModel?
}