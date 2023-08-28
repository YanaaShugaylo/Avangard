package com.avangard.bratstvo.education.root.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.education.root.domain.GetEducationCategoriesUseCase
import com.avangard.bratstvo.education.root.domain.GetLessonsUserCase
import com.avangard.bratstvo.education.root.domain.mapper.EducationUiModelMapper
import com.avangard.bratstvo.education.root.domain.model.EducationCategory
import com.avangard.bratstvo.education.root.presentation.model.EducationItemUiModel
import com.avangard.bratstvo.home.domain.HasInternetUseCase
import kotlinx.coroutines.launch

class EducationViewModel(
    private val getEducationCategories: GetEducationCategoriesUseCase,
    private val getLessons: GetLessonsUserCase,
    hasInternet: HasInternetUseCase,
    private val educationUiModelMapper: EducationUiModelMapper
) : ViewModel() {

    val navigateToLesson = MutableLiveData<Int>()
    val isCategoryScreen = MutableLiveData(false)
    val educationUi = MutableLiveData<List<EducationItemUiModel>>()
    val isOnline = MutableLiveData(true)

    private val baseViewModelDelegate = BaseViewModelDelegate(this)
    private val categories = ArrayList<EducationCategory>()

    init {
        isOnline.value = hasInternet()

        if (isOnline.value == true) {
            baseViewModelDelegate.coroutineScope.launch {
                MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)
                categories.addAll(getEducationCategories())
                educationUi.value = categories.mapNotNull { educationUiModelMapper.map(it) }
                MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
            }
        }
    }

    fun onItemClick(id: Int, isCategory: Boolean) {
        if (isCategory) {
            if (isCategoryScreen.value == false) {
                baseViewModelDelegate.coroutineScope.launch {
                    MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)

                    val currentCategory = categories.first { it.id == id }
                    val tasks = getLessons(id)
                    val uiModel = mutableListOf(educationUiModelMapper.map(currentCategory)!!)
                    uiModel.addAll(tasks.mapNotNull { educationUiModelMapper.map(it) })
                    educationUi.value = uiModel

                    MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
                }
                isCategoryScreen.value = true
            }
        } else {
            navigateToLesson.value = id
        }
    }

    fun onBackClick() {
        setCategoriesList()
    }

    fun navigationComplete() {
        navigateToLesson.value = 0
    }

    private fun setCategoriesList() {
        educationUi.value = categories.mapNotNull { educationUiModelMapper.map(it) }
        isCategoryScreen.value = false
    }
}