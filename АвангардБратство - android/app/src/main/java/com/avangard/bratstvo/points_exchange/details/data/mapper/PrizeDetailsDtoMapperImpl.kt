package com.avangard.bratstvo.points_exchange.details.data.mapper

import com.avangard.bratstvo.points_exchange.details.data.model.PrizeDetailsDto
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeDetailsDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeRewardDtoMapper
import com.avangard.bratstvo.points_exchange.details.domain.model.PrizeDetails
import com.avangard.bratstvo.tasks.root.domain.mapper.ImageAttachmentDtoMapper

class PrizeDetailsDtoMapperImpl(
    private val imageAttachmentDtoMapper: ImageAttachmentDtoMapper,
    private val prizeRewardsDtoMapper: PrizeRewardDtoMapper
) : PrizeDetailsDtoMapper {

    override fun map(dto: PrizeDetailsDto) = PrizeDetails(
        dto.id,
        dto.title,
        dto.price,
        dto.isActive,
        dto.endDate,
        dto.description,
        dto.rewardDescription,
        dto.backColor,
        imageAttachmentDtoMapper.map(dto.backgroundImage),
        imageAttachmentDtoMapper.map(dto.image),
        dto.rewards.map { prizeRewardsDtoMapper.map(it) }
    )
}