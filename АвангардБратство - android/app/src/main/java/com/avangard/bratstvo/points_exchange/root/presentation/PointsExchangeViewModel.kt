package com.avangard.bratstvo.points_exchange.root.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.points_exchange.root.domain.GetPrizesCategoriesUseCase
import com.avangard.bratstvo.points_exchange.root.domain.GetPrizesUseCase
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizeUiMapper
import com.avangard.bratstvo.points_exchange.root.domain.mapper.PrizesCategoryUiMapper
import com.avangard.bratstvo.points_exchange.root.domain.model.PrizesCategory
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizeUiModel
import com.avangard.bratstvo.points_exchange.root.presentation.model.PrizesCategoryUiModel
import kotlinx.coroutines.launch

class PointsExchangeViewModel(
    private val getPrizesCategories: GetPrizesCategoriesUseCase,
    private val getPrizes: GetPrizesUseCase,
    private val prizesCategoryUiMapper: PrizesCategoryUiMapper,
    private val prizeUiMapper: PrizeUiMapper
) : ViewModel() {

    val categoriesUi = MutableLiveData<List<PrizesCategoryUiModel>>()
    val prizesUi = MutableLiveData<List<PrizeUiModel>>()

    var lastPage = false
    var loadingStatus = LoadingStatus.NONE

    private val baseViewModelDelegate = BaseViewModelDelegate(this)
    private var currentCategory = 0

    init {
        baseViewModelDelegate.coroutineScope.launch {
            setLoading()
            val categories = mutableListOf(PrizesCategory(CATEGORY_ID_ALL, "Все"))
            categories.addAll(getPrizesCategories())

            val boughtPrizes = getPrizes(getBought = true)

            if (boughtPrizes.prizes.isNotEmpty()) {
                categories.add(1, PrizesCategory(CATEGORY_ID_BOUGHT, "Купленные"))
            }

            val categoriesItems = categories.map { prizesCategoryUiMapper.map(it) }
            categoriesItems[0].isChoosen = true

            categoriesUi.value = categoriesItems
            loadCurrentCategoryPrizes()
            setNotLoading()
        }
    }

    fun loadMorePrizes() {
        categoriesUi.value?.let {
            baseViewModelDelegate.coroutineScope.launch {
                setLoading()
                val prizesUiNew = ArrayList<PrizeUiModel>()
                prizesUiNew.addAll(prizesUi.value!!)
                val prizesList = when {
                    it[currentCategory].id >= 0 -> getPrizes(it[currentCategory].id, page = (prizesUiNew.size / 10) + 1)
                    it[currentCategory].id == CATEGORY_ID_BOUGHT -> {
                        getPrizes(getBought = true, page = (prizesUiNew.size / 10) + 1)
                    }
                    else -> getPrizes(page = (prizesUiNew.size / 10) + 1)
                }

                lastPage = prizesList.isLastPage

                prizesUiNew.addAll(prizesList.prizes.map { prizeUiMapper.map(it) })
                prizesUi.value = prizesUiNew
                setNotLoading()
            }
        }
    }

    fun onCategoryItemClick(position: Int) {
        if (currentCategory != position) {
            categoriesUi.value?.let {
                val newList = ArrayList<PrizesCategoryUiModel>()
                newList.addAll(categoriesUi.value!!)

                for (i in newList.indices) {
                    when (i) {
                        currentCategory -> newList[i] = PrizesCategoryUiModel(
                            newList[i].id,
                            newList[i].title,
                            false
                        )
                        position -> newList[i] = PrizesCategoryUiModel(
                            newList[i].id,
                            newList[i].title,
                            true
                        )
                    }
                }

                currentCategory = position
                loadCurrentCategoryPrizes()
                categoriesUi.value = newList
            }
        }
    }

    private fun loadCurrentCategoryPrizes() {
        categoriesUi.value?.let {
            baseViewModelDelegate.coroutineScope.launch {
                setLoading()
                val prizesList = when {
                    it[currentCategory].id == CATEGORY_ID_BOUGHT -> getPrizes(getBought = true)
                    it[currentCategory].id >= 0 -> getPrizes(it[currentCategory].id)
                    else -> getPrizes()
                }

                Log.i("myLog", "prizesList = $prizesList")

                lastPage = prizesList.isLastPage

                prizesUi.value = prizesList.prizes.map { prizeUiMapper.map(it) }
                Log.i("myLog", "prizesUi = ${prizesUi.value}")
                setNotLoading()
            }
        }
    }

    private suspend fun setLoading() {
        loadingStatus = LoadingStatus.LOADING
        MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)
    }

    private suspend fun setNotLoading() {
        MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
        loadingStatus = LoadingStatus.DONE
    }

    private companion object {
        const val CATEGORY_ID_ALL = -1
        const val CATEGORY_ID_BOUGHT = -2
    }
}