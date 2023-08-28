package com.avangard.bratstvo.tasks.details

import com.avangard.bratstvo.tasks.details.data.TaskDetailsApi
import com.avangard.bratstvo.tasks.details.data.TaskDetailsRepositoryImpl
import com.avangard.bratstvo.tasks.details.data.mapper.TaskDetailsUiMapperImpl
import com.avangard.bratstvo.tasks.details.data.mapper.TaskStatusMapperImpl
import com.avangard.bratstvo.tasks.details.data.mapper.TaskTypeMapperImpl
import com.avangard.bratstvo.tasks.details.data.mapper.TaskUserDataDtoMapperImpl
import com.avangard.bratstvo.tasks.details.domain.GetTaskDetailsUseCase
import com.avangard.bratstvo.tasks.details.domain.TaskActivateUseCase
import com.avangard.bratstvo.tasks.details.domain.TaskDetailsRepository
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskDetailsUiMapper
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskStatusMapper
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskTypeMapper
import com.avangard.bratstvo.tasks.details.domain.mapper.TaskUserDataDtoMapper
import com.avangard.bratstvo.tasks.details.presentation.TaskDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun tasksDetailsModule() = module {
    factory<TaskDetailsApi> { get<Retrofit>().create() }

    factory<TaskDetailsUiMapper> { TaskDetailsUiMapperImpl(get(), get()) }
    factory<TaskTypeMapper> { TaskTypeMapperImpl() }
    factory<TaskStatusMapper> { TaskStatusMapperImpl() }
    factory<TaskUserDataDtoMapper> { TaskUserDataDtoMapperImpl() }

    factory<TaskDetailsRepository> { TaskDetailsRepositoryImpl(get(), get(), get()) }

    factory { GetTaskDetailsUseCase(get()) }
    factory { TaskActivateUseCase(get()) }

    viewModel { TaskDetailsViewModel(get(), get(), get()) }
}