package com.avangard.bratstvo.home.data.mapper

import com.avangard.bratstvo.home.domain.mapper.HomeItemMapper
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.avangard.bratstvo.tasks.root.domain.model.Task

class HomeItemMapperImpl : HomeItemMapper {

    override fun map(domainModel: Any) = when(domainModel) {
        is Int -> HomeItemModel.Points(domainModel)
        is Task -> {
            HomeItemModel.Task(
                domainModel.id,
                domainModel.taskDetails.title,
                domainModel.taskDetails.pointsReward,
                domainModel.taskDetails.category?.title ?: ""
            )
        }
        else -> HomeItemModel.Title(domainModel.toString())
    }
}