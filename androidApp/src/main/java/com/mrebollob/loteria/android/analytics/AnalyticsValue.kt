package com.mrebollob.loteria.android.analytics

import android.os.Bundle
import java.io.Serializable

data class AnalyticsValue(
    val parameter: AnalyticsParameter,
    val value: Serializable
) {

    fun appendToBundle(bundle: Bundle) {
        if (value is Boolean) {
            bundle.putBoolean(
                parameter.key,
                value
            )
        } else if (value is Int) {
            bundle.putInt(
                parameter.key,
                value
            )
        } else {
            bundle.putString(
                parameter.key,
                value.toString()
            )
        }
    }
}
