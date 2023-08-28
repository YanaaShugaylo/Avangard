package com.avangard.bratstvo.education.root.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.education.root.domain.EducationRepository
import com.avangard.bratstvo.education.root.domain.mapper.EducationCategoriesListDtoMapper
import com.avangard.bratstvo.education.root.domain.mapper.LessonsListDtoMapper
import kotlinx.coroutines.withContext

class EducationRepositoryImpl(
    private val api: EducationApi,
    private val educationCategoriesListDtoMapper: EducationCategoriesListDtoMapper,
    private val lessonsListDtoMapper: LessonsListDtoMapper,
    private val appDispatchers: AppDispatchers
) : EducationRepository {

    override suspend fun getCategories() = withContext(appDispatchers.network) {
        educationCategoriesListDtoMapper.map(api.getEducationCategories().data)
    }

    override suspend fun getLessons(categoryId: Int) = withContext(appDispatchers.network) {
        lessonsListDtoMapper.map(api.getLessons(categoryId).data)
    }
}