package com.paizuri.widgetapp.helpers

import android.view.View
import android.widget.EditText

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.setOnClick(onClickListener: () -> Unit) {
    this.setOnClickListener {
        onClickListener()
    }
}

fun EditText.isEmpty() = this.text.isEmpty()
