package com.example.movieapps.utils

import android.view.View
import java.text.SimpleDateFormat
import java.util.*

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun String.displayDate(): String? {
    val localeID = Locale("in")
    val date = SimpleDateFormat("yyyy-MM-dd", localeID).parse(this)
    val format = SimpleDateFormat("dd/MM/yyyy", localeID)
    return date?.let {
        format.format(it)
    }
}