package com.mrebollob.loteria.android.presentation.platform.extension

import java.text.SimpleDateFormat
import java.util.Date

fun Date.getStringDate(): String = with(this) {
    val format = SimpleDateFormat("d MMMM, yyyy")
    format.format(this.time)
}
