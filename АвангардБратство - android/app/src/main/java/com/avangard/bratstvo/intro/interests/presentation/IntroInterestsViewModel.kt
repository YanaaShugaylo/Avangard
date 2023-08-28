package com.avangard.bratstvo.intro.interests.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.authorization.root.domain.SaveIsAuthorizedUseCase
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.intro.interests.domain.GetInterestsUseCase
import com.avangard.bratstvo.intro.interests.domain.SaveInterestsUseCase
import com.avangard.bratstvo.intro.interests.domain.mapper.InterestsListUiMapper
import com.avangard.bratstvo.intro.interests.domain.model.IntroSecondStepData
import com.avangard.bratstvo.intro.interests.presentation.model.InterestUiModel
import kotlinx.coroutines.launch

class IntroInterestsViewModel(
    private val getInterests: GetInterestsUseCase,
    private val saveInterests: SaveInterestsUseCase,
    private val saveIsAuthorizedUseCase: SaveIsAuthorizedUseCase,
    private val interestsListUiMapper: InterestsListUiMapper
) : ViewModel() {

    val readyToNavigateNext = MutableLiveData<Boolean>()
    val interestsUiModel = MutableLiveData<List<InterestUiModel>>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    init {
        baseViewModelDelegate.coroutineScope.launch {
            interestsUiModel.value = interestsListUiMapper.map(getInterests())
        }
    }

    fun onActionButtonClick() {
        baseViewModelDelegate.coroutineScope.launch {
            if (interestsUiModel.value?.none { it.chosen } == true) {
                saveIsAuthorizedUseCase()
                readyToNavigateNext.value = true
            } else {
                val interestsIds = interestsUiModel.value?.filter { it.chosen }?.map { it.id }

                if (!interestsIds.isNullOrEmpty()) {
                    val isRequestSucceed = saveInterests(IntroSecondStepData(interestsIds))
                    if (isRequestSucceed) saveIsAuthorizedUseCase()
                    readyToNavigateNext.value = isRequestSucceed
                }
            }
        }
    }
}