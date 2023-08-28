package com.avangard.bratstvo.home.domain.mapper

import com.avangard.bratstvo.home.presentation.model.HomeItemModel

interface HomeItemMapper {

    fun map(domainModel: Any): HomeItemModel
}