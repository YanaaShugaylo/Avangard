package com.avangard.bratstvo.education.root.data.mapper

import com.avangard.bratstvo.education.root.data.model.EducationCategoryDto
import com.avangard.bratstvo.education.root.domain.mapper.EducationCategoriesListDtoMapper
import com.avangard.bratstvo.education.root.domain.mapper.EducationCategoryDtoMapper

class EducationCategoriesListDtoMapperImpl(
    private val educationCategoryDtoMapper: EducationCategoryDtoMapper
) : EducationCategoriesListDtoMapper {

    override fun map(dtoList: List<EducationCategoryDto>) = dtoList.map {
        educationCategoryDtoMapper.map(it)
    }
}