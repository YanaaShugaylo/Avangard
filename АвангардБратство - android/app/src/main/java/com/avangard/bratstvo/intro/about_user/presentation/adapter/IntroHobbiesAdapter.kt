package com.avangard.bratstvo.intro.about_user.presentation.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.avangard.bratstvo.R
import com.avangard.bratstvo.databinding.InterestChipLayoutBinding
import com.avangard.bratstvo.intro.about_user.presentation.model.HobbyUiModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class IntroHobbiesAdapter(
    private val chipGroup: ChipGroup,
    private val onChipClick: (String?) -> Unit
) {

    var items: List<HobbyUiModel>? = null
        set(value) {
            field = value
            notifyDatasetChanged()
        }

    private lateinit var chipBinding: InterestChipLayoutBinding

    fun notifyDatasetChanged() {
        setChips()
    }

    private fun setChips() {
        items?.let {
            for (i in it.indices) {
                chipGroup.addView(getChip(it[i].title, i))
            }
        }
    }

    private fun getChip(item: String, position: Int): Chip {
        chipBinding = InterestChipLayoutBinding.inflate(LayoutInflater.from(chipGroup.context))

        chipBinding.root.text = item
        chipBinding.root.setOnClickListener {
            changeChipChosen(position)
        }

        return chipBinding.root
    }

    private fun changeChipChosen(index: Int) {
        items?.let {
            it[index].chosen = if (it[index].chosen) {
                (chipGroup[index] as Chip).chipBackgroundColor = getChipBgColorState(false)
                false
            } else {
                (chipGroup[index] as Chip).chipBackgroundColor = getChipBgColorState(true)
                true
            }

            onChipClick(null)
        }
    }

    private fun getChipBgColorState(isChosen: Boolean): ColorStateList {
        return if (isChosen) {
            ColorStateList.valueOf(ContextCompat.getColor(chipGroup.context, R.color.extra_accent_color))
        } else {
            ColorStateList.valueOf(ContextCompat.getColor(chipGroup.context, R.color.base_inputfield_background_color))
        }
    }
}