package com.mrebollob.loteria.android.presentation.platform.extension

import androidx.annotation.StringRes
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.domain.entity.SortingMethod

@StringRes
fun SortingMethod.getNameString(): Int = with(this) {
    when (this) {
        SortingMethod.NAME -> R.string.settings_screen_tickets_sorting_method_name
        SortingMethod.NUMBER -> R.string.settings_screen_tickets_sorting_method_number
    }
}
