package com.avangard.bratstvo.start

import android.text.InputFilter
import android.text.Spanned

class EditTextInputFilter(private val regex: Regex) : InputFilter {

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        return if (regex.containsMatchIn(source ?: "")) {
            ""
        } else {
            null
        }
    }
}