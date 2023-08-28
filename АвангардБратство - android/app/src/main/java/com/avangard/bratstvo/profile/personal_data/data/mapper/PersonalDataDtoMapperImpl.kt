package com.avangard.bratstvo.profile.personal_data.data.mapper

import com.avangard.bratstvo.base.extensions.domain.string.getDateFromUtc
import com.avangard.bratstvo.profile.personal_data.data.model.UserPersonalDataDto
import com.avangard.bratstvo.profile.personal_data.domain.mapper.PersonalDataDtoMapper
import com.avangard.bratstvo.profile.personal_data.domain.model.UserPersonalData
import com.avangard.bratstvo.user.domain.mapper.HobbiesSimpleListDtoMapper
import com.avangard.bratstvo.user.domain.mapper.InterestsSimpleListDtoMapper

class PersonalDataDtoMapperImpl(
    private val hobbiesSimpleListDtoMapper: HobbiesSimpleListDtoMapper,
    private val interestsSimpleListDtoMapper: InterestsSimpleListDtoMapper
) : PersonalDataDtoMapper {

    override fun map(dto: UserPersonalDataDto) = UserPersonalData(
        (dto.birthDate ?: "").getDateFromUtc("dd MMMM yyyy"),
        dto.education ?: "",
        dto.about ?: "",
        dto.city ?: "",
        dto.fullName ?: "",
        dto.balance,
        dto.photoUrl ?: "",
        hobbiesSimpleListDtoMapper.map(dto.hobbies),
        interestsSimpleListDtoMapper.map(dto.interests)
    )
}