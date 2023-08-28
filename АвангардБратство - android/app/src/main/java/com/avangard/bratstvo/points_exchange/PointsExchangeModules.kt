package com.avangard.bratstvo.points_exchange

import com.avangard.bratstvo.points_exchange.details.pointsExchangeDetailsModule
import com.avangard.bratstvo.points_exchange.root.pointsExchangeRootModule

fun pointsExchangeModules() = listOf(
    pointsExchangeRootModule(),
    pointsExchangeDetailsModule()
)