package com.avangard.bratstvo.tasks.details.presentation.model

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.avangard.bratstvo.R

data class TaskDetailsUiModel(
    val id: Int,
    val title: String,
    val dateStatusText: String,
    val backgroundImageLink: String,
    val points: Int,
    val descriptionLink: String,
    val status: TasksStatuses,
    val button: TaskDetailsActionButton,
    val type: TasksTypes
)

class TaskDetailsActionButton(
    @StringRes val stringRes: Int,
    @ColorRes val textColorRes: Int,
    @DrawableRes val backgroundRes: Int,
) {
    companion object {
        fun getActionButtonModel(status: TasksStatuses) = TaskDetailsActionButton(
            getActionButtonTextResource(status),
            getActionButtonTextColorResource(status),
            getActionButtonBackgroundResource(status),
        )

        private fun getActionButtonTextResource(status: TasksStatuses): Int = when(status) {
            TasksStatuses.NOT_ACTIVATED -> R.string.tasks_details_action_button_not_activated
            TasksStatuses.ACTIVATED -> R.string.tasks_details_action_button_activated
            TasksStatuses.COMPLETED -> R.string.tasks_details_action_button_answered
            TasksStatuses.APPROVED -> R.string.tasks_details_action_button_approved
        }

        private fun getActionButtonTextColorResource(status: TasksStatuses): Int = when(status) {
            TasksStatuses.NOT_ACTIVATED -> R.color.white
            TasksStatuses.ACTIVATED -> R.color.white
            TasksStatuses.COMPLETED, TasksStatuses.APPROVED -> R.color.base_button_text_color
        }

        private fun getActionButtonBackgroundResource(status: TasksStatuses): Int = when(status) {
            TasksStatuses.NOT_ACTIVATED -> R.drawable.base_button_bg
            TasksStatuses.ACTIVATED -> R.drawable.base_button_bg
            TasksStatuses.COMPLETED, TasksStatuses.APPROVED -> R.drawable.button_bordered_bg_state_common
        }
    }
}

enum class TasksTypes {
    COMMENT, FILE, FILE_AND_COMMENT
}

enum class TasksStatuses {
    APPROVED, COMPLETED, ACTIVATED, NOT_ACTIVATED
}