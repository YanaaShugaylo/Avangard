package com.avangard.bratstvo.base.root.domain

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.avangard.bratstvo.base.root.presentation.model.ToolbarStateModel

object MainActivityStateHelper {

    private const val DEFAULT_BOTTOM_NAVIGATION_VISIBILITY = View.GONE

    val toolbarState = MutableLiveData<ToolbarStateModel>()
    val bottomNavigationVisibility = MutableLiveData<Int>(DEFAULT_BOTTOM_NAVIGATION_VISIBILITY)
}