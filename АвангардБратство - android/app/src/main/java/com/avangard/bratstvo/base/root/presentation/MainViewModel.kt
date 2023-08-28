package com.avangard.bratstvo.base.root.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.authorization.root.domain.IsUserAuthorizedUseCase
import com.avangard.bratstvo.base.exceptions.domain.BaseException
import com.avangard.bratstvo.home.domain.HasInternetUseCase
import com.avangard.bratstvo.profile.personal_data.domain.GetPersonalDataUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUser: GetPersonalDataUseCase,
    isUserAuthorized: IsUserAuthorizedUseCase,
    hasInternet: HasInternetUseCase,
) : ViewModel() {

    val profileImage = MutableLiveData<String?>(null)

    val isOnline = MutableLiveData(true)

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    init {
        isOnline.value = hasInternet()

        if (isUserAuthorized() && isOnline.value == true) {
            baseViewModelDelegate.coroutineScope.launch {
                try {
                    profileImage.postValue(getUser().photoUrl)
                } catch (e: BaseException) {

                }
            }
        }
    }
}