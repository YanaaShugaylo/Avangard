package com.avangard.bratstvo.profile.root.data.mapper

import com.avangard.bratstvo.profile.root.data.model.UserSkillDto
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillDtoMapper
import com.avangard.bratstvo.profile.root.domain.model.UserSkill

class UserSkillDtoMapperImpl : UserSkillDtoMapper {

    override fun map(dto: UserSkillDto) = UserSkill(dto.title, dto.total, dto.progress)
}