package com.avangard.bratstvo.tasks.root.data.mapper

import com.avangard.bratstvo.tasks.details.domain.mapper.TaskStatusMapper
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskUiModelMapper
import com.avangard.bratstvo.tasks.root.domain.model.Task
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory
import com.avangard.bratstvo.tasks.root.presentation.model.TasksItemUiModel

class TaskUiModelMapperImpl(private val taskStatusMapper: TaskStatusMapper) : TaskUiModelMapper {

    override fun map(domainModel: Any) = when(domainModel) {
        is TasksCategory -> {
            TasksItemUiModel.Category(
                domainModel.id,
                domainModel.title,
                getTasksPercent(domainModel.taskActiveAndCompletedCount, domainModel.tasksActiveCount),
                domainModel.attachment?.originalUrl
            )
        }
        is Task -> {
            TasksItemUiModel.Common(
                domainModel.id,
                domainModel.taskDetails.title,
                domainModel.taskDetails.pointsReward,
                taskStatusMapper.map(domainModel.statusText),
                1
            )
        }
        is TaskDetails -> {
            TasksItemUiModel.Common(
                domainModel.id,
                domainModel.title,
                domainModel.pointsReward,
                taskStatusMapper.map(domainModel.taskUserData?.statusText),
                1
            )
        }
        else -> null
    }

    private fun getTasksPercent(tasksCompletedCount: Int, tasksAllCount: Int): Int {
        return ((tasksCompletedCount.toFloat() / tasksAllCount) * 100).toInt()
    }
}