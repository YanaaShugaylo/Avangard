package com.avangard.bratstvo.points_exchange.details.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.points_exchange.details.domain.BuyPrizeUseCase
import com.avangard.bratstvo.points_exchange.details.domain.GetPrizeDetailsUseCase
import com.avangard.bratstvo.points_exchange.details.domain.mapper.PrizeDetailsUiMapper
import com.avangard.bratstvo.points_exchange.details.presentation.model.PrizeDetailsUiModel
import com.avangard.bratstvo.user.domain.GetUserBalanceUseCase
import kotlinx.coroutines.launch

class PointsExchangeDetailsViewModel(
    private val getPrizeDetails: GetPrizeDetailsUseCase,
    private val buyPrize: BuyPrizeUseCase,
    private val getUserPoints: GetUserBalanceUseCase,
    private val prizeDetailsUiMapper: PrizeDetailsUiMapper
) : ViewModel() {

    val screenState = MutableLiveData(ScreenState.MAIN)
    val prizeUi = MutableLiveData<PrizeDetailsUiModel>()

    var isLoading = MutableLiveData(false)

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    fun loadPrize(prizeId: Int?) {
        baseViewModelDelegate.coroutineScope.launch {
            prizeUi.value = if (prizeId != null) {
                prizeDetailsUiMapper.map(getPrizeDetails(prizeId))
            } else {
                null
            }
        }
    }

    fun onBuyPrizeClick() {
        baseViewModelDelegate.coroutineScope.launch {
            MainActivityInteractionsHelper.setLoaderVisibility(true)
            isLoading.value = true
            if (prizeUi.value?.id != null) {
                if (prizeUi.value!!.price < getUserPoints()) {
                    buyPrize(prizeUi.value!!.id)
                } else {
                    MainActivityInteractionsHelper.showMessage("Недостаточно баллов")
                }
            }
            MainActivityInteractionsHelper.setLoaderVisibility(false)
            isLoading.value = false
        }
    }

    fun onPromocodesClick() {
        if (screenState.value != ScreenState.LIST) {
            screenState.value = ScreenState.LIST
        }
    }

    fun onQrClick() {
        if (screenState.value != ScreenState.QR) {
            screenState.value = ScreenState.QR
        }
    }

    fun onBackClick() {
        screenState.value = when(screenState.value) {
            ScreenState.LIST -> ScreenState.MAIN
            ScreenState.QR -> ScreenState.LIST
            else -> ScreenState.MAIN
        }
    }
}

enum class ScreenState {
    MAIN, LIST, QR
}