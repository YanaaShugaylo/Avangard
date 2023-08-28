package com.avangard.bratstvo.education.root

import com.avangard.bratstvo.education.root.data.EducationApi
import com.avangard.bratstvo.education.root.data.EducationRepositoryImpl
import com.avangard.bratstvo.education.root.data.mapper.EducationCategoriesListDtoMapperImpl
import com.avangard.bratstvo.education.root.data.mapper.EducationCategoryDtoMapperImpl
import com.avangard.bratstvo.education.root.data.mapper.EducationUiModelMapperImpl
import com.avangard.bratstvo.education.root.data.mapper.LessonDtoMapperImpl
import com.avangard.bratstvo.education.root.data.mapper.LessonsListDtoMapperImpl
import com.avangard.bratstvo.education.root.domain.EducationRepository
import com.avangard.bratstvo.education.root.domain.GetEducationCategoriesUseCase
import com.avangard.bratstvo.education.root.domain.GetLessonsUserCase
import com.avangard.bratstvo.education.root.domain.mapper.EducationCategoriesListDtoMapper
import com.avangard.bratstvo.education.root.domain.mapper.EducationCategoryDtoMapper
import com.avangard.bratstvo.education.root.domain.mapper.EducationUiModelMapper
import com.avangard.bratstvo.education.root.domain.mapper.LessonDtoMapper
import com.avangard.bratstvo.education.root.domain.mapper.LessonsListDtoMapper
import com.avangard.bratstvo.education.root.presentation.EducationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create

internal fun educationRootModule() = module {
    factory<EducationApi> { get<Retrofit>().create() }

    factory<EducationCategoriesListDtoMapper> { EducationCategoriesListDtoMapperImpl(get()) }
    factory<EducationCategoryDtoMapper> { EducationCategoryDtoMapperImpl(get()) }
    factory<LessonsListDtoMapper> { LessonsListDtoMapperImpl(get()) }
    factory<LessonDtoMapper> { LessonDtoMapperImpl(get()) }
    factory<EducationUiModelMapper> { EducationUiModelMapperImpl() }

    factory<EducationRepository> { EducationRepositoryImpl(get(), get(), get(), get()) }

    factory { GetEducationCategoriesUseCase(get()) }
    factory { GetLessonsUserCase(get()) }

    viewModel { EducationViewModel(get(), get(), get(), get()) }
}