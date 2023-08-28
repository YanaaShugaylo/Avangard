package com.avangard.bratstvo.tasks.root.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.home.domain.HasInternetUseCase
import com.avangard.bratstvo.tasks.root.domain.GetTasksCategoriesUseCase
import com.avangard.bratstvo.tasks.root.domain.GetTasksUseCase
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskUiModelMapper
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory
import com.avangard.bratstvo.tasks.root.presentation.model.TasksItemUiModel
import kotlinx.coroutines.launch

class TasksViewModel(
    private val getTasksCategories: GetTasksCategoriesUseCase,
    private val getTasks: GetTasksUseCase,
    hasInternet: HasInternetUseCase,
    private val taskUiModelMapper: TaskUiModelMapper
) : ViewModel() {

    val navigateToTask = MutableLiveData<Int>()
    val isCategoryScreen = MutableLiveData(false)
    val tasksUi = MutableLiveData<List<TasksItemUiModel>>()
    val isOnline = MutableLiveData(true)

    private val baseViewModelDelegate = BaseViewModelDelegate(this)
    private val categories = ArrayList<TasksCategory>()

    init {
        isOnline.value = hasInternet()

        if (isOnline.value == true) {
            baseViewModelDelegate.coroutineScope.launch {
                MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)

                categories.addAll(getTasksCategories())
                tasksUi.value = categories.mapNotNull { taskUiModelMapper.map(it) }

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
                    val tasks = getTasks(id)
                    Log.i("myLog", "tasks = $tasks")
                    val uiModel = mutableListOf(taskUiModelMapper.map(currentCategory)!!)
                    Log.i("myLog", "uiModel = $uiModel")
                    uiModel.addAll(tasks.mapNotNull { taskUiModelMapper.map(it) })
                    Log.i("myLog", "uiModel = $uiModel")
                    tasksUi.value = uiModel

                    MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
                }
                isCategoryScreen.value = true
            }
        } else {
            navigateToTask.value = id
        }
    }

    fun onBackClick() {
        setCategoriesList()
    }

    fun navigationComplete() {
        navigateToTask.value = 0
    }

    private fun setCategoriesList() {
        tasksUi.value = categories.mapNotNull { taskUiModelMapper.map(it) }
        isCategoryScreen.value = false
    }
}