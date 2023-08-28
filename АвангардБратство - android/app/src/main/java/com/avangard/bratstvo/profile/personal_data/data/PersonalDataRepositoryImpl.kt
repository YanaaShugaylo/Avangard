package com.avangard.bratstvo.profile.personal_data.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.profile.personal_data.data.model.UserPersonalDataDto
import com.avangard.bratstvo.profile.personal_data.domain.PersonalDataRepository
import com.avangard.bratstvo.profile.personal_data.domain.mapper.PersonalDataDtoMapper
import kotlinx.coroutines.withContext
import java.lang.Exception

class PersonalDataRepositoryImpl(
    private val api: PersonalDataApi,
    private val personalDataDtoMapper: PersonalDataDtoMapper,
    private val appDispatchers: AppDispatchers
) : PersonalDataRepository {

    override suspend fun getData() = withContext(appDispatchers.network) {
        var result: UserPersonalDataDto

        try {
            result = api.getData().data
        } catch (e: Exception) {
            result = UserPersonalDataDto(
                "",
                "",
                "",
                "",
                "",
                0,
                "",
                listOf(),
                listOf()
            )
        }

        personalDataDtoMapper.map(result)
    }
}