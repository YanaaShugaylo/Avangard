package com.avangard.bratstvo.base.root.presentation

import android.view.View
import com.avangard.bratstvo.base.root.domain.MainActivityStateHelper
import com.avangard.bratstvo.base.root.presentation.model.ToolbarStateModel
import com.avangard.bratstvo.base.root.presentation.model.ToolbarButtonsStates

class BaseFragmentDelegate(
    var screenTitle: String,
    var screenToolbarButtonsState: ToolbarButtonsStates = ToolbarButtonsStates.COMMON_STATE,
    var screenBottomNavigationVisibility: Int = View.GONE,

) {

    fun initialize() {
        notifyDataSetChanged()
    }

    fun notifyDataSetChanged() {
        MainActivityStateHelper.toolbarState.value = ToolbarStateModel(
            screenToolbarButtonsState,
            screenTitle
        )

        MainActivityStateHelper.bottomNavigationVisibility.value = screenBottomNavigationVisibility
    }
}