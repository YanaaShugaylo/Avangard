package com.avangard.bratstvo.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.coroutines.AppDispatchers
import com.avangard.bratstvo.base.exceptions.domain.BaseException
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.home.domain.HasInternetUseCase
import com.avangard.bratstvo.home.domain.mapper.HomeItemMapper
import com.avangard.bratstvo.home.presentation.model.HomeItemModel
import com.avangard.bratstvo.tasks.root.domain.GetTasksDailyUseCase
import com.avangard.bratstvo.tasks.root.domain.model.Task
import com.avangard.bratstvo.user.domain.GetUserBalanceUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.plus
import java.lang.Exception

class HomeViewModel(
    private val getUserBalance: GetUserBalanceUseCase,
    private val getTasksDaily: GetTasksDailyUseCase,
    hasInternet: HasInternetUseCase,
    private val homeItemMapper: HomeItemMapper,
    appDispatchers: AppDispatchers
) : ViewModel() {

    val navigateToType = MutableLiveData<HomeNavigationTypes?>()
    val homeUi = MutableLiveData<List<HomeItemModel>>()
    val isOnline = MutableLiveData(true)

    var itemId = 0

    val error = MutableLiveData<Pair<String, Boolean>?>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    init {
        isOnline.value = hasInternet()

        if (isOnline.value == true) {
            combine(getUserBalanceAsFlow(), getTasksDailyAsFlow()) { balance, tasks ->
                val balanceItem = homeItemMapper.map(balance)
                val tasksItems = tasks.map { homeItemMapper.map(it) }

                val uiItems = ArrayList<HomeItemModel>()
                uiItems.add(balanceItem)
                uiItems.add(HomeItemModel.Title("Задания на день"))
                uiItems.addAll(tasksItems)
                uiItems
            }.onEach {
                homeUi.postValue(it)
            }.launchIn(baseViewModelDelegate.coroutineScope + appDispatchers.computing)
        }
    }

    fun onItemClick(item: HomeItemModel) {
        navigateToType.value = when(item) {
            is HomeItemModel.Points -> HomeNavigationTypes.POINTS_EXCHANGE
            is HomeItemModel.Task -> {
                itemId = item.id
                HomeNavigationTypes.TASK
            }
            is HomeItemModel.CardWithBackground -> HomeNavigationTypes.LESSON
            else -> null
        }
    }

    fun navigationComplete() {
        navigateToType.value = null
    }

    fun logoutComplete() {
        error.postValue(null)
    }

    private fun getUserBalanceAsFlow(): Flow<Int> = flow {
        var balance = 0

        try {
            balance = getUserBalance()
        } catch (e: Exception) {
            if (e is BaseException && (e.code == 401 || e.code == 422)) {
                error.postValue(Pair(e.message ?: "", true))
            }
        }

        emit(balance)
    }

    private fun getTasksDailyAsFlow(): Flow<List<Task>> = flow {
        emit(getTasksDaily())
    }
}

enum class HomeNavigationTypes {
    POINTS_EXCHANGE, TASK, LESSON, SUPPORT
}