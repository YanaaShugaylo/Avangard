package com.avangard.bratstvo.user.domain.mapper

import com.avangard.bratstvo.user.data.model.BalanceDto
import com.avangard.bratstvo.user.domain.model.Balance

interface UserBalanceDtoMapper {
    fun map(dto: BalanceDto): Balance
}