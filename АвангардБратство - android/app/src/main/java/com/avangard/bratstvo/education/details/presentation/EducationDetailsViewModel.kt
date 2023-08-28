package com.avangard.bratstvo.education.details.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.education.details.domain.GetLessonDetailsUseCase
import com.avangard.bratstvo.education.details.domain.mapper.LessonDetailsUiMapper
import com.avangard.bratstvo.education.details.presentation.model.LessonDetailsUiModel
import kotlinx.coroutines.launch

class EducationDetailsViewModel(
    private val getLessonDetails: GetLessonDetailsUseCase,
    private val lessonDetailsUiMapper: LessonDetailsUiMapper
) : ViewModel() {

    val lessonUi = MutableLiveData<LessonDetailsUiModel?>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    fun loadTask(taskId: Int?) {
        baseViewModelDelegate.coroutineScope.launch {
            MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)
            lessonUi.value = if (taskId != null) {
                val lessonDetails = getLessonDetails(taskId)
                lessonDetailsUiMapper.map(lessonDetails)
            } else {
                null
            }

            MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
        }
    }
}