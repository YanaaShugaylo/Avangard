package com.avangard.bratstvo.tasks.done

import com.avangard.bratstvo.tasks.done.data.TaskDoneApi
import com.avangard.bratstvo.tasks.done.data.TaskDoneRepositoryImpl
import com.avangard.bratstvo.tasks.done.data.mapper.TaskDoneItemMapperImpl
import com.avangard.bratstvo.tasks.done.data.mapper.TaskResultDataDtoMapperImpl
import com.avangard.bratstvo.tasks.done.domain.SendTaskResultUseCase
import com.avangard.bratstvo.tasks.done.domain.TaskDoneRepository
import com.avangard.bratstvo.tasks.done.domain.mapper.TaskDoneItemMapper
import com.avangard.bratstvo.tasks.done.domain.mapper.TaskResultDataDtoMapper
import com.avangard.bratstvo.tasks.done.presentation.TaskDoneViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun tasksDoneModule() = module {
    factory<TaskDoneApi> { get<Retrofit>().create() }

    factory<TaskDoneItemMapper> { TaskDoneItemMapperImpl(get()) }
    factory<TaskResultDataDtoMapper> { TaskResultDataDtoMapperImpl() }

    factory<TaskDoneRepository> { TaskDoneRepositoryImpl(get(), get(), get()) }

    factory { SendTaskResultUseCase(get()) }

    viewModel { TaskDoneViewModel(get(), get(), get(), get()) }
}