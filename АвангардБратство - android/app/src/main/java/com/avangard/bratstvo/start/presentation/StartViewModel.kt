package com.avangard.bratstvo.start.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.authorization.root.domain.AuthorizeUserUseCase
import com.avangard.bratstvo.authorization.root.domain.GetAuthTokenUseCase
import com.avangard.bratstvo.authorization.root.domain.IsUserAuthorizedUseCase
import com.avangard.bratstvo.authorization.root.domain.mapper.AuthorizationResultUiMapper
import com.avangard.bratstvo.authorization.root.domain.model.UserCredentials
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.start.presentation.model.AuthorizationResultUiModel
import kotlinx.coroutines.launch

class StartViewModel(
    private val authorizeUser: AuthorizeUserUseCase,
    private val isUserAuthorized: IsUserAuthorizedUseCase,
    private val authorizationResultUiMapper: AuthorizationResultUiMapper
) : ViewModel() {

    val authorizationResult = MutableLiveData<AuthorizationResultUiModel>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    init {
        baseViewModelDelegate.coroutineScope.launch {
            if (isUserAuthorized()) {
                authorizationResult.value = AuthorizationResultUiModel(isAuthSucceed = true, isFirstTime = false)
            }
        }
    }

    fun onLoginButtonClick(login: String?, password: String?) {
        if (!login.isNullOrEmpty() && !password.isNullOrEmpty()) {
            baseViewModelDelegate.coroutineScope.launch {
                val authorize = authorizeUser(UserCredentials(login, password))
                authorizationResult.value = authorizationResultUiMapper.map(authorize)
            }
        } else {
            authorizationResult.value = AuthorizationResultUiModel(isAuthSucceed = false, isFirstTime = true)
        }
    }
}