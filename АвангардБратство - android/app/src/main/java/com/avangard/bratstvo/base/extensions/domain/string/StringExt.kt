package com.avangard.bratstvo.base.extensions.domain.string

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.getDateFromUtc(outPattern: String = "dd.MM.yyyy"): String {
    return if (!isNullOrEmpty()) {
        val date: Date? = getFormatter().parse(this)

        val newFormatter = SimpleDateFormat(outPattern, Locale.getDefault())
        newFormatter.format(date)
    } else {
        this
    }
}

fun getFormatter(
    pattern: String = "yyyy-MM-dd HH:mm:ss"
) = SimpleDateFormat(pattern, Locale.getDefault())