package com.avangard.bratstvo.education.details.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.education.details.domain.LessonDetailsRepository
import com.avangard.bratstvo.education.root.domain.mapper.LessonDtoMapper
import com.avangard.bratstvo.education.root.domain.model.Lesson
import kotlinx.coroutines.withContext
import java.lang.Exception

class LessonDetailsRepositoryImpl(
    private val api: LessonDetailsApi,
    private val mapper: LessonDtoMapper,
    private val appDispatchers: AppDispatchers
) : LessonDetailsRepository {

    override suspend fun getLessonDetails(lessonId: Int) = withContext(appDispatchers.network) {
        var lesson: Lesson? = null

        try {
            lesson = mapper.map(api.getLessonDetails(lessonId).data)
        } catch (e: Exception) {

        }

        lesson
    }
}