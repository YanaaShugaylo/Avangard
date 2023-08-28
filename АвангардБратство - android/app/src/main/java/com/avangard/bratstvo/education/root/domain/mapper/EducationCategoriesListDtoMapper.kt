package com.avangard.bratstvo.education.root.domain.mapper

import com.avangard.bratstvo.education.root.data.model.EducationCategoryDto
import com.avangard.bratstvo.education.root.domain.model.EducationCategory

interface EducationCategoriesListDtoMapper {

    fun map(dtoList: List<EducationCategoryDto>): List<EducationCategory>
}