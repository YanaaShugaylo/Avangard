package com.avangard.bratstvo.profile.personal_data.presentation.adapter

import android.view.LayoutInflater
import com.avangard.bratstvo.databinding.InterestChipLayoutBinding
import com.avangard.bratstvo.user.domain.model.InterestSimpleModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class PersonalDataInterestsAdapter(private val chipGroup: ChipGroup) {

    var items: List<InterestSimpleModel>? = null
        set(value) {
            field = value
            notifyDatasetChanged()
        }

    private lateinit var chipBinding: InterestChipLayoutBinding

    fun notifyDatasetChanged() {
        setChips()
    }

    private fun setChips() {
        items?.forEach {
            chipGroup.addView(getChip(it.title))
        }
    }

    private fun getChip(item: String): Chip {
        chipBinding = InterestChipLayoutBinding.inflate(LayoutInflater.from(chipGroup.context))

        chipBinding.root.text = item
        chipBinding.root.isClickable = false
        chipBinding.root.isFocusable = false

        return chipBinding.root
    }
}