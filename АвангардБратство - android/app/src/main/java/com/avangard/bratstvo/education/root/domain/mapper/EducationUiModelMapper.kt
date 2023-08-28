package com.avangard.bratstvo.education.root.domain.mapper

import com.avangard.bratstvo.education.root.presentation.model.EducationItemUiModel

interface EducationUiModelMapper {

    fun map(domainModel: Any): EducationItemUiModel?
}