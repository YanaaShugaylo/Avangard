package com.avangard.bratstvo.base.root.domain

import androidx.lifecycle.MutableLiveData
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object MainActivityInteractionsHelper : KoinComponent {

    val message = MutableLiveData<String?>()
    val loaderVisibility = MutableLiveData<Boolean>(false)

    private val appDispatchers by inject<AppDispatchers>()

    fun showMessage(msg: String) {
        message.postValue(msg)
    }

    fun setMessageShowed() {
        message.postValue(null)
    }

    suspend fun setLoaderVisibility(isVisible: Boolean) {
        withContext(appDispatchers.ui) {
            loaderVisibility.value = isVisible
        }
    }
}