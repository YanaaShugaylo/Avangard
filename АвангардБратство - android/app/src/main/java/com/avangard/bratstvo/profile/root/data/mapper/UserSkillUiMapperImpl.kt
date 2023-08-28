package com.avangard.bratstvo.profile.root.data.mapper

import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillUiMapper
import com.avangard.bratstvo.profile.root.domain.model.UserSkill
import com.avangard.bratstvo.profile.root.presentation.model.UserSkillUiModel

class UserSkillUiMapperImpl : UserSkillUiMapper {

    override fun map(domainModel: UserSkill) = UserSkillUiModel(
        domainModel.title,
        domainModel.progress,
        domainModel.total
    )
}