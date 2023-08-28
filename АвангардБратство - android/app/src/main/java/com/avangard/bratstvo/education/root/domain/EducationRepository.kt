package com.avangard.bratstvo.education.root.domain

import com.avangard.bratstvo.education.root.domain.model.EducationCategory
import com.avangard.bratstvo.education.root.domain.model.Lesson

interface EducationRepository {

    suspend fun getCategories(): List<EducationCategory>
    suspend fun getLessons(categoryId: Int): List<Lesson>
}