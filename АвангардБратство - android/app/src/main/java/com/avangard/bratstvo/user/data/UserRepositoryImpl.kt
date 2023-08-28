package com.avangard.bratstvo.user.data

import android.util.Log
import com.avangard.bratstvo.authorization.root.domain.AuthorizationRepository
import com.avangard.bratstvo.authorization.root.domain.GetAuthTokenUseCase
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.exceptions.domain.AuthFailedException
import com.avangard.bratstvo.base.exceptions.domain.BaseException
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.user.data.model.BalanceDto
import com.avangard.bratstvo.user.data.model.UserDto
import com.avangard.bratstvo.user.domain.mapper.UserBalanceDtoMapper
import com.avangard.bratstvo.user.domain.UserRepository
import com.avangard.bratstvo.user.domain.mapper.UserDtoMapper
import com.avangard.bratstvo.user.domain.model.User
import com.google.gson.Gson
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

class UserRepositoryImpl(
    private val api: UserApi,
    private val userDtoMapper: UserDtoMapper,
    private val userBalanceDtoMapper: UserBalanceDtoMapper,
    private val gson: Gson,
    private val authorizationRepository: AuthorizationRepository,
    private val appDispatchers: AppDispatchers,
    private val getAuthToken: GetAuthTokenUseCase
) : UserRepository {

    override suspend fun getUser() = withContext(appDispatchers.network) {
        var response: UserDto? = null
        val errorMessage: String

        try {
            response = api.getUser().data
        } catch (e: Exception) {
            if (e.cause is HttpException) {
                errorMessage = when (e) {
                    is AuthFailedException -> {
                        authorizationRepository.logout()
                        "Ошибка авторизации"
                    }
                    else -> "Что-то пошло не так"
                }

                throw BaseException(message = errorMessage, code = (e.cause as HttpException).code())
            }
        }

        val result = userDtoMapper.map(response!!)

        result
    }

    override suspend fun getBalance() = withContext(appDispatchers.network) {
        MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)
        var response: BalanceDto? = null
        val errorMessage: String

        try {
            response = api.getBalance(getAuthToken() ?: "").data
        } catch (e: Exception) {
            Log.i("myLog", "e = $e")
            Log.i("myLog", "e cause = ${e.cause}")
            errorMessage = when (e) {
                is AuthFailedException -> {
                    authorizationRepository.logout()
                    "Ошибка авторизации"
                }
                else -> "Что-то пошло не так"
            }

            MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
            throw BaseException(message = errorMessage, code = (e.cause as HttpException).code())
        }

        val result = userBalanceDtoMapper.map(response)

        MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)

        result
    }

    override suspend fun saveLocal(user: User) {

    }
}