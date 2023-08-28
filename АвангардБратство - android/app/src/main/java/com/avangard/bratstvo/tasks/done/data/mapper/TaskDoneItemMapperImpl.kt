package com.avangard.bratstvo.tasks.done.data.mapper

import com.avangard.bratstvo.R
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskTypeMapper
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.details.presentation.model.TasksTypes
import com.avangard.bratstvo.tasks.done.domain.mapper.TaskDoneItemMapper
import com.avangard.bratstvo.tasks.done.presentation.model.TaskDoneItemModel

class TaskDoneItemMapperImpl(private val taskTypeMapper: TaskTypeMapper) : TaskDoneItemMapper {

    override fun map(domainModel: TaskDetails?) = if (domainModel == null) {
        null
    } else {
        val items = ArrayList<TaskDoneItemModel>()

        val type = taskTypeMapper.map(domainModel.type)
        val title = when (type) {
            TasksTypes.COMMENT -> R.string.tasks_done_title_comment
            TasksTypes.FILE -> R.string.tasks_done_title_file
            TasksTypes.FILE_AND_COMMENT -> R.string.tasks_done_title_file_and_comment
        }

        items.add(TaskDoneItemModel.Title(title))

        if (type == TasksTypes.COMMENT || type == TasksTypes.FILE_AND_COMMENT) {
            val inputHint = when (type) {
                TasksTypes.COMMENT -> R.string.tasks_done_input_hint_comment
                else -> R.string.tasks_done_input_hint_file_and_comoment
            }

            items.add(TaskDoneItemModel.InputField(inputHint, ""))
        }

        if (type == TasksTypes.FILE || type == TasksTypes.FILE_AND_COMMENT) {
            val buttonText = "Загрузить фото или видео"

            items.add(TaskDoneItemModel.ButtonFile(buttonText))
        }

        items.add(TaskDoneItemModel.ButtonAction(R.string.tasks_done_button_action_text))

        items
    }
}