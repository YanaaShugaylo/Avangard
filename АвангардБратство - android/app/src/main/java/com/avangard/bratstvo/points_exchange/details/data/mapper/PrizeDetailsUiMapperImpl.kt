package com.avangard.bratstvo.points_exchange.details.data.mapper

import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardUiMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeDetailsUiMapper
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeReward
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeRewardsTypes
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeDetailsUiModel
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeRewardUiModel

class PrizeDetailsUiMapperImpl(private val rewardMapper: PrizeRewardUiMapper) : PrizeDetailsUiMapper {

    override fun map(domainModel: PrizeDetails?) = if (domainModel == null) {
        null
    } else {
        PrizeDetailsUiModel(
            domainModel.id,
            domainModel.title,
            domainModel.price,
            domainModel.isActive,
            domainModel.endDate,
            domainModel.description,
            domainModel.backColor,
            domainModel.backgroundImage?.originalUrl ?: "",
            domainModel.image?.originalUrl ?: "",
            getRewardsList(domainModel.rewards, domainModel.rewardDescription)
        )
    }

    private fun getRewardsList(rewards: List<PrizeReward>, instruction: String?): List<PrizeRewardUiModel> {
        val rewardsUi = ArrayList<PrizeRewardUiModel>()
        rewardsUi.addAll(rewards.map {
            rewardMapper.map(it, oneCode = rewards.size == 1)
        })

        if (rewardsUi.isNotEmpty()) {
            rewardsUi.add(
                rewardMapper.map(
                    PrizeReward(
                        type = PrizeRewardsTypes.NOTHING,
                        value = instruction ?: "",
                        boughtDate = "",
                        isActive = true
                    )
                )
            )
        }

        return rewardsUi
    }
}