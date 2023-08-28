package com.avangard.bratstvo.tasks.root.data

import android.util.Log
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.tasks.details.domain.model.TaskDetails
import com.avangard.bratstvo.tasks.root.domain.TasksRepository
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDetailsDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoriesListDtoMapper
import com.avangard.bratstvo.tasks.root.domain.model.Task
import com.avangard.bratstvo.tasks.root.domain.model.TasksCategory
import kotlinx.coroutines.withContext
import java.lang.Exception

class TasksRepositoryImpl(
    private val api: TasksApi,
    private val tasksCategoriesListDtoMapper: TasksCategoriesListDtoMapper,
    private val taskDtoMapper: TaskDtoMapper,
    private val taskDetailsDtoMapper: TaskDetailsDtoMapper,
    private val appDispatchers: AppDispatchers
) : TasksRepository {

    override suspend fun getCategories() = withContext(appDispatchers.network) {
        var categories: List<TasksCategory> = emptyList()

        try {
            categories = tasksCategoriesListDtoMapper.map(api.getCategories().data)
        } catch (e: Exception) {

        }

        categories
    }

    override suspend fun getTasks(categoryId: Int) = withContext(appDispatchers.network) {
        var tasks: List<TaskDetails> = emptyList()

        try {
            val result = api.getTasks(categoryId).data
            Log.i("myLog", "result = $result")
            tasks = result.tasks.map { taskDetailsDtoMapper.map(it) }
            Log.i("myLog", "tasks = $tasks")
        } catch (e: Exception) {

        }

        tasks
    }

    override suspend fun getTasksDaily() = withContext(appDispatchers.network) {
        var tasks: List<Task> = emptyList()

        try {
            val tasksDto = api.getTasksDaily().data
            tasks = tasksDto.map { taskDtoMapper.map(it) }
        } catch (e: Exception) {
        }

        tasks
    }
}