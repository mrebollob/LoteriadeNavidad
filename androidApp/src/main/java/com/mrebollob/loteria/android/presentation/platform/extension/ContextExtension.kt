package com.mrebollob.loteria.android.presentation.platform.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent

fun Context.toast(text: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, duration).show()

fun Context.loadCustomTabs(uri: Uri) = with(this) {
    val builder = CustomTabsIntent.Builder()
    builder.build().launchUrl(this, uri)
}

fun Context.sendEmail(addresses: Array<String>, subject: String, text: String): Boolean {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, addresses)
        putExtra(Intent.EXTRA_SUBJECT, subject)
        putExtra(Intent.EXTRA_TEXT, text)
    }

    return if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
        true
    } else {
        false
    }
}
