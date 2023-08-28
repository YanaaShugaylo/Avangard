package com.avangard.bratstvo.tasks.details.data.mapper

import com.avangard.bratstvo.tasks.details.domain.mapper.TaskDetailsUiMapper
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskStatusMapper
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskTypeMapper
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.details.presentation.model.TaskDetailsActionButton
import com.avangard.bratstvo.tasks.details.presentation.model.TaskDetailsUiModel
import com.avangard.bratstvo.tasks.details.presentation.model.TasksStatuses

class TaskDetailsUiMapperImpl(
    private val taskTypeMapper: TaskTypeMapper,
    private val taskStatusMapper: TaskStatusMapper
) : TaskDetailsUiMapper {

    override fun map(domainModel: TaskDetails?) = if (domainModel == null) {
        null
    } else {
        val status = taskStatusMapper.map(domainModel.taskUserData?.statusText)

        TaskDetailsUiModel(
            domainModel.id,
            domainModel.title,
            getDateOrStatus(domainModel.endDate, status),
            domainModel.attachment?.originalUrl ?: "",
            domainModel.pointsReward,
            domainModel.descriptionLink,
            status,
            TaskDetailsActionButton.getActionButtonModel(status),
            taskTypeMapper.map(domainModel.type)
        )
    }

    private fun getDateOrStatus(date: String, status: TasksStatuses): String {
        return if (status == TasksStatuses.COMPLETED) {
            "Выполнено"
        } else {
            date
        }
    }
}