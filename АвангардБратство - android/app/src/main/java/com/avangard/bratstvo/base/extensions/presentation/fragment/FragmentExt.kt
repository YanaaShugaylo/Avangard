package com.avangard.bratstvo.base.extensions.presentation.fragment

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.hideKeyboard() {
    val imm = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = requireActivity().currentFocus ?: View(requireActivity())

    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.getDrawable(context: Context, @DrawableRes resId: Int): Drawable? {
    return ContextCompat.getDrawable(context, resId)
}

fun Fragment.getColor(context: Context, @ColorRes resId: Int): Int {
    return ContextCompat.getColor(context, resId)
}