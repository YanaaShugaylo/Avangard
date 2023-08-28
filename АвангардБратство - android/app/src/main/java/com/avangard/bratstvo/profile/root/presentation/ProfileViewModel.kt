package com.avangard.bratstvo.profile.root.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.exceptions.domain.BaseException
import com.avangard.bratstvo.base.root.domain.MainActivityInteractionsHelper
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.profile.root.domain.GetUserRatingUseCase
import com.avangard.bratstvo.profile.root.domain.GetUserSkillsUseCase
import com.avangard.bratstvo.profile.root.domain.mapper.UserRatingUiMapper
import com.avangard.bratstvo.profile.root.domain.mapper.UserSkillUiMapper
import com.avangard.bratstvo.profile.root.presentation.model.UserSkillUiModel
import com.avangard.bratstvo.profile.root.presentation.model.UserRatingUiModel
import com.avangard.bratstvo.user.domain.GetUserBalanceUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserRating: GetUserRatingUseCase,
    private val getUserSkills: GetUserSkillsUseCase,
    private val getUserHistory: GetUserHistoryUseCase,
    private val getUserBalance: GetUserBalanceUseCase,
    private val userRatingUiMapper: UserRatingUiMapper,
    private val userSkillUiMapper: UserSkillUiMapper,
    private val userNewBalanceUiMapper: UserNewBalanceUiMapper,
    private val userHistoryUiMapper: UserHistoryUiMapper,
    private val getUserNewBalance: GetUserNewBalanceUseCase
) : ViewModel() {

    val ratingUi = MutableLiveData<UserRatingUiModel>()
    val balanceUi = MutableLiveData<Int>()
    val skillsUi = MutableLiveData<List<UserSkillUiModel>>()
    val newBalanceUi = MutableLiveData<UserNewBalanceUiModel>()
    val historyUi = MutableLiveData<UserHistoryUiModel>()

    val logoutOnFailure = MutableLiveData<Boolean>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    init {
        baseViewModelDelegate.coroutineScope.launch {
            MainActivityInteractionsHelper.setLoaderVisibility(isVisible = true)

            runCatching {
                val points = getUserBalance()

                ratingUi.value = userRatingUiMapper.map(getUserRating())
                balanceUi.value = points
                val skills = getUserSkills()
                skillsUi.value = skills.map { userSkillUiMapper.map(it) }
                newBalanceUi.value = userNewBalanceUiMapper.map(getUserNewBalance())
                historyUi.value = userHistoryUiMapper.map(getUserHistory())

                MainActivityInteractionsHelper.setLoaderVisibility(isVisible = false)
            }.onFailure {
                if (it is BaseException && it.code == 401) {
                    logoutOnFailure.postValue(true)
                }
            }
        }
    }

    fun logoutComplete() {
        logoutOnFailure.postValue(false)
    }
}