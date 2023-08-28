package com.avangard.bratstvo.profile.root.data.mapper

import com.avangard.bratstvo.profile.root.data.model.UserSkillDto
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillDtoMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillsListDtoMapper

class UserSkillsListDtoMapperImpl(
    private val userSkillDtoMapper: UserSkillDtoMapper
) : UserSkillsListDtoMapper {

    override fun map(dtoList: List<UserSkillDto>) = dtoList.map {
        userSkillDtoMapper.map(it)
    }
}