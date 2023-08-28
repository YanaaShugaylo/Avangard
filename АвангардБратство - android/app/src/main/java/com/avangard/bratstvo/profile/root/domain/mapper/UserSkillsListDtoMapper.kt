package com.avangard.bratstvo.profile.root.domain.mapper

import com.avangard.bratstvo.profile.root.data.model.UserSkillDto
import com.avangard.bratstvo.profile.root.domain.model.UserSkill

interface UserSkillsListDtoMapper {

    fun map(dtoList: List<UserSkillDto>): List<UserSkill>
}