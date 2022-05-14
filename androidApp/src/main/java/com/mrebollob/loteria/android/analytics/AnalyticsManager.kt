package com.mrebollob.loteria.android.analytics

import android.os.Bundle
import androidx.compose.ui.text.intl.Locale
import co.touchlab.kermit.Logger
import com.google.firebase.analytics.FirebaseAnalytics

class AnalyticsManager(
    private val firebaseAnalytics: FirebaseAnalytics
) {

    fun trackEvent(event: AnalyticsEvent, bundle: Bundle? = null) {
        Logger.d { "trackEvent: ${event.key} -> ${bundle ?: "Empty params"}" }
        firebaseAnalytics.logEvent(event.key, bundle)
    }

    fun trackEvent(
        event: AnalyticsEvent,
        vararg parameters: AnalyticsValue
    ) {
        val paramsBundle = Bundle()
        for (value in parameters) {
            value.appendToBundle(paramsBundle)
        }

        trackEvent(event, paramsBundle)
    }

    fun setUserData() {
        firebaseAnalytics.setUserProperty("language", Locale.current.language)
        firebaseAnalytics.setUserProperty("device", android.os.Build.DEVICE)
        firebaseAnalytics.setUserProperty("manufacturer", android.os.Build.MANUFACTURER)
        firebaseAnalytics.setUserProperty("model", android.os.Build.MODEL)
        firebaseAnalytics.setUserProperty("product", android.os.Build.PRODUCT)
    }
}
