package com.avangard.bratstvo.profile.root.domain.mapper

import com.avangard.bratstvo.profile.root.data.model.UserSkillDto
import com.avangard.bratstvo.profile.root.domain.model.UserSkill

interface UserSkillDtoMapper {

    fun map(dto: UserSkillDto): UserSkill
}