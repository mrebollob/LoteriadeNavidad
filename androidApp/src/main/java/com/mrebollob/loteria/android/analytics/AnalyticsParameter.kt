package com.mrebollob.loteria.android.analytics

import kotlin.math.min

enum class AnalyticsParameter(val key: String) {
    CURRENT_LOCATION("current_location"),
    SORTING_METHOD("sorting_method");

    fun withStringValue(value: String): AnalyticsValue = AnalyticsValue(
        this,
        value.substring(0, min(value.length, 30))
    )

    fun withBooleanValue(value: Boolean): AnalyticsValue = AnalyticsValue(this, value)

    fun withIntValue(value: Int): AnalyticsValue = AnalyticsValue(this, value)

    fun withScreenValue(screen: AnalyticsScreen): AnalyticsValue = AnalyticsValue(this, screen.key)
}

