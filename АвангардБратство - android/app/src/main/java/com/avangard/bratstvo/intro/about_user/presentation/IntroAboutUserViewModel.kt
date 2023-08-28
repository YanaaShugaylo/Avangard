package com.avangard.bratstvo.intro.about_user.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avangard.bratstvo.base.root.presentation.BaseViewModelDelegate
import com.avangard.bratstvo.intro.about_user.domain.GetHobbiesUseCase
import com.avangard.bratstvo.intro.about_user.domain.SaveHobbiesUseCase
import com.avangard.bratstvo.intro.about_user.domain.mapper.HobbiesListUiMapper
import com.avangard.bratstvo.intro.about_user.domain.model.IntroFirstStepData
import com.avangard.bratstvo.intro.about_user.presentation.model.HobbyUiModel
import kotlinx.coroutines.launch

class IntroAboutUserViewModel(
    private val getHobbies: GetHobbiesUseCase,
    private val saveHobbies: SaveHobbiesUseCase,
    private val hobbiesListUiMapper: HobbiesListUiMapper
) : ViewModel() {

    val isUserDataEmpty = MutableLiveData(true)
    val readyToNavigateNext = MutableLiveData<Boolean>()
    val hobbiesUiModel = MutableLiveData<List<HobbyUiModel>>()

    private val baseViewModelDelegate = BaseViewModelDelegate(this)

    private var about: String? = null

    init {
        baseViewModelDelegate.coroutineScope.launch {
            hobbiesUiModel.value = hobbiesListUiMapper.map(getHobbies())
        }
    }

    fun dataHasChanged(text: String?) {
        if (text != null) {
            about = text
        }

        isUserDataEmpty.value = getChosenHobbiesIds().isNullOrEmpty() && about.isNullOrEmpty()
    }

    fun onActionButtonClick(about: String?) {
        baseViewModelDelegate.coroutineScope.launch {
            if (about.isNullOrEmpty() || hobbiesUiModel.value?.none { it.chosen } == true) {
                readyToNavigateNext.value = true
            } else {
                val hobbiesIds = getChosenHobbiesIds()

                if (!hobbiesIds.isNullOrEmpty()) {
                    val isRequestSucceed = saveHobbies(IntroFirstStepData(about, hobbiesIds))
                    readyToNavigateNext.value = isRequestSucceed
                }
            }
        }
    }

    private fun getChosenHobbiesIds(): List<Int>? = hobbiesUiModel.value?.filter { it.chosen }?.map { it.id }
}