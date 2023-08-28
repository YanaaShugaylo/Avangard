package com.avangard.bratstvo.user.domain

import com.avangard.bratstvo.user.domain.model.Balance
import com.avangard.bratstvo.user.domain.model.User

interface UserRepository {

    suspend fun getUser(): User
    @Throws
    suspend fun getBalance(): Balance
    suspend fun saveLocal(user: User)
}