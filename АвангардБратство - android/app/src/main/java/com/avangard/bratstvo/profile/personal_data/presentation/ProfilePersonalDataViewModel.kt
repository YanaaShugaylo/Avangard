package com.avangard.bratstvo.profile.personal_data.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.profile.personal_data.domain.GetPersonalDataUseCase
import com.avangard.bratstvo.profile.personal_data.domain.LogoutUserUseCase
import com.avangard.bratstvo.profile.personal_data.domain.mapper.PersonalDataUiMapper
import com.avangard.bratstvo.profile.personal_data.presentation.model.PersonalDataItemModel
import kotlinx.coroutines.launch

class ProfilePersonalDataViewModel(
    private val getPersonalData: GetPersonalDataUseCase,
    private val personalDataUiMapper: PersonalDataUiMapper,
    private val logoutUser: LogoutUserUseCase
) : ViewModel() {

    val privateDataUi = MutableLiveData<List<PersonalDataItemModel>>()
    val navigateToLogin = MutableLiveData<Boolean>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    init {
        baseViewModelDelegate.coroutineScope.launch {
            privateDataUi.value = personalDataUiMapper.map(getPersonalData())
        }
    }

    fun logoutButtonClicked() {
        if (logoutUser()) {
            navigateToLogin.value = true
        }
    }

    fun logoutCompleted() {
        navigateToLogin.value = false
    }
}