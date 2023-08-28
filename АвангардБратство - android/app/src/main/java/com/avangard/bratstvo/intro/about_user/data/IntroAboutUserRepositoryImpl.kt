package com.avangard.bratstvo.intro.about_user.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbiesListDtoMapper
import com.avangard.bratstvo.intro.about_user.domain.IntroAboutUserRepository
import com.avangard.bratstvo.intro.about_user.domain.mapper.IntroFirstStepMapper
import com.avangard.bratstvo.intro.about_user.domain.model.Hobby
import com.avangard.bratstvo.intro.about_user.domain.model.IntroFirstStepData
import kotlinx.coroutines.withContext
import java.lang.Exception

class IntroAboutUserRepositoryImpl(
    private val api: IntroAboutUserApi,
    private val hobbiesListDtoMapper: HobbiesListDtoMapper,
    private val introFirstStepMapper: IntroFirstStepMapper,
    private val appDispatchers: AppDispatchers
) : IntroAboutUserRepository {

    override suspend fun getHobbies(): List<Hobby> = withContext(appDispatchers.network) {
        var hobbies: List<Hobby> = emptyList()

        try {
            hobbies = hobbiesListDtoMapper.map(api.getHobbies().data)
        } catch (e: Exception) {

        }

        hobbies
    }

    override suspend fun saveHobbies(data: IntroFirstStepData): Boolean = withContext(appDispatchers.network) {
        var result: BaseBackendResponse<*>? = null

        try {
            result = api.saveHobbies(introFirstStepMapper.map(data))
        } catch (e: Exception) {

        }

        result != null
    }
}