package com.avangard.bratstvo.education.details

import com.avangard.bratstvo.education.details.data.LessonDetailsApi
import com.avangard.bratstvo.education.details.data.LessonDetailsRepositoryImpl
import com.avangard.bratstvo.education.details.data.mapper.LessonDetailsUiMapperImpl
import com.avangard.bratstvo.education.details.domain.GetLessonDetailsUseCase
import com.avangard.bratstvo.education.details.domain.LessonDetailsRepository
import com.avangard.bratstvo.education.details.domain.mapper.LessonDetailsUiMapper
import com.avangard.bratstvo.education.details.presentation.EducationDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

fun educationDetailsModule() = module {
    factory<LessonDetailsApi> { get<Retrofit>().create() }

    factory<LessonDetailsUiMapper> { LessonDetailsUiMapperImpl() }

    factory<LessonDetailsRepository> { LessonDetailsRepositoryImpl(get(), get(), get()) }

    factory { GetLessonDetailsUseCase(get()) }

    viewModel { EducationDetailsViewModel(get(), get()) }
}