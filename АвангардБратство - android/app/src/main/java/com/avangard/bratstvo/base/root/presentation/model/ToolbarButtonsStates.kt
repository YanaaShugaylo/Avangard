package com.avangard.bratstvo.base.root.presentation.model

enum class ToolbarButtonsStates {
    COMMON_STATE, ONLY_BACK_STATE, MAIN_STATE, NOTHING, MY_STATE
}

data class ToolbarStateModel(
    val state: ToolbarButtonsStates = ToolbarButtonsStates.COMMON_STATE,
    val titleText: String = ""


) {
    companion object {
        fun getEmptyModel() = ToolbarStateModel(ToolbarButtonsStates.NOTHING)
    }
}