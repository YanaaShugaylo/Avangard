package com.avangard.bratstvo.tasks.done.presentation.model

import androidx.annotation.StringRes

open class TaskDoneItemModel {

    internal class Title(
        @StringRes val textRes: Int
    ) : TaskDoneItemModel()

    internal class InputField(
        @StringRes val hintRes: Int,
        var text: String
    ) : TaskDoneItemModel()

    internal class ButtonFile(
        val text: String
    ) : TaskDoneItemModel()

    internal class ButtonAction(
        @StringRes val textRes: Int,
        val isContentLoaded: Boolean = false
    ) : TaskDoneItemModel()
}