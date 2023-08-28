package com.avangard.bratstvo.profile.root.data

import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.profile.root.domain.ProfileRepository
import com.avangard.bratstvo.profile.root.domain.UserNewBalance
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingDtoMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillsListDtoMapper
import com.avangard.bratstvo.profile.root.presentation.UserHistory
import kotlinx.coroutines.withContext

class ProfileRepositoryImpl(
    private val api: ProfileApi,
    private val userRatingDtoMapper: UserRatingDtoMapper,
    private val usernewBalanceDtoMapper: UserNewBalanceDtoMapper,
    private val userHistoryDtoMapper: UserHistoryDtoMapper,
    private val userSkillsListDtoMapper: UserSkillsListDtoMapper,
    private val appDispatchers: AppDispatchers
) : ProfileRepository {

    override suspend fun getRating() = withContext(appDispatchers.network) {
        userRatingDtoMapper.map(api.getRating().data)
    }

    override suspend fun getHistory() = withContext(appDispatchers.network){
        userHistoryDtoMapper.map(api.getHistory().data)
    }

    override suspend fun getNewBalance() = withContext(appDispatchers.network) {

        usernewBalanceDtoMapper.map(api.getNewBalance().data)
    }

    override suspend fun getSkills() = withContext(appDispatchers.network) {
        userSkillsListDtoMapper.map(api.getSkills().data)
    }
}