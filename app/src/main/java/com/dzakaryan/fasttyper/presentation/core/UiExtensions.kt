package com.dzakaryan.fasttyper.presentation.core

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.showSoftKeyboard() {
    if (requestFocus()) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Activity.hideSoftKeyboard() {
    val currentFocus = currentFocus
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let { focus ->
        imm.hideSoftInputFromWindow(focus.windowToken, 0)
        focus.clearFocus()
    }
}