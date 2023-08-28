package com.avangard.bratstvo.tasks.root

import com.avangard.bratstvo.tasks.root.data.TasksApi
import com.avangard.bratstvo.tasks.root.data.TasksRepositoryImpl
import com.avangard.bratstvo.tasks.root.data.mapper.ImageAttachmentDtoMapperImpl
import com.avangard.bratstvo.tasks.root.data.mapper.TaskDetailsDtoMapperImpl
import com.avangard.bratstvo.tasks.root.data.mapper.TaskDtoMapperImpl
import com.avangard.bratstvo.tasks.root.data.mapper.TaskUiModelMapperImpl
import com.avangard.bratstvo.tasks.root.data.mapper.TasksCategoriesListDtoMapperImpl
import com.avangard.bratstvo.tasks.root.data.mapper.TasksCategoryDtoMapperImpl
import com.avangard.bratstvo.tasks.root.data.mapper.TasksListDtoMapperImpl
import com.avangard.bratstvo.tasks.root.domain.GetTasksCategoriesUseCase
import com.avangard.bratstvo.tasks.root.domain.GetTasksDailyUseCase
import com.avangard.bratstvo.tasks.root.domain.GetTasksUseCase
import com.avangard.bratstvo.tasks.root.domain.TasksRepository
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDetailsDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TaskUiModelMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoriesListDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksCategoryDtoMapper
import com.avangard.bratstvo.tasks.root.domain.mapper.TasksListDtoMapper
import com.avangard.bratstvo.tasks.root.presentation.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun tasksRootModule() = module {
    factory<TasksApi> { get<Retrofit>().create() }

    factory<TasksCategoriesListDtoMapper> { TasksCategoriesListDtoMapperImpl(get()) }
    factory<TasksCategoryDtoMapper> { TasksCategoryDtoMapperImpl(get()) }
    factory<TasksListDtoMapper> { TasksListDtoMapperImpl(get()) }
    factory<TaskDtoMapper> { TaskDtoMapperImpl(get()) }
    factory<TaskDetailsDtoMapper> { TaskDetailsDtoMapperImpl(get(), get(), get()) }
    factory<ImageAttachmentDtoMapper> { ImageAttachmentDtoMapperImpl() }
    factory<TaskUiModelMapper> { TaskUiModelMapperImpl(get()) }

    factory<TasksRepository> { TasksRepositoryImpl(get(), get(), get(), get(), get()) }

    factory { GetTasksCategoriesUseCase(get()) }
    factory { GetTasksUseCase(get()) }
    factory { GetTasksDailyUseCase(get()) }

    viewModel { TasksViewModel(get(), get(), get(), get()) }
}