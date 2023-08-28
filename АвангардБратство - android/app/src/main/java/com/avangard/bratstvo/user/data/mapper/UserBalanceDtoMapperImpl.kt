package com.avangard.bratstvo.user.data.mapper

import com.avangard.bratstvo.user.data.model.BalanceDto
import com.avangard.bratstvo.user.domain.mapper.UserBalanceDtoMapper
import com.avangard.bratstvo.user.domain.model.Balance

class UserBalanceDtoMapperImpl : UserBalanceDtoMapper {

    override fun map(dto: BalanceDto): Balance = Balance(dto.count)
}