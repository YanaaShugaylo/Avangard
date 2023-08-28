package com.avangard.bratstvo.intro.interests.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.root.data.BaseBackendResponse
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestsListDtoMapper
import com.avangard.bratstvo.intro.interests.domain.IntroInterestsRepository
import com.avangard.bratstvo.intro.interests.domain.mapper.IntroSecondStepMapper
import com.avangard.bratstvo.intro.interests.domain.model.Interest
import com.avangard.bratstvo.intro.interests.domain.model.IntroSecondStepData
import kotlinx.coroutines.withContext
import java.lang.Exception

class IntroInterestsRepositoryImpl(
    private val api: IntroInterestsApi,
    private val interestsListDtoMapper: InterestsListDtoMapper,
    private val introSecondStepMapper: IntroSecondStepMapper,
    private val appDispatchers: AppDispatchers
) : IntroInterestsRepository {

    override suspend fun getInterests(): List<Interest> = withContext(appDispatchers.network) {
        var interests: List<Interest> = emptyList()

        try {
            interests = interestsListDtoMapper.map(api.getInterests().data)
        } catch (e: Exception) {

        }

        interests
    }

    override suspend fun saveInterests(data: IntroSecondStepData): Boolean = withContext(appDispatchers.network) {
        var result: BaseBackendResponse<*>? = null

        try {
            result = api.saveInterests(introSecondStepMapper.map(data))
        } catch (e: Exception) {

        }

        result != null
    }
}