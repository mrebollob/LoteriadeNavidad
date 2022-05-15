package com.mrebollob.loteria.android.presentation.settings

sealed class SettingsRoute(val route: String) {
    object Menu : SettingsRoute("menu")
    object Share : SettingsRoute("share")
    object ManageTickets : SettingsRoute("manage_tickets")
}
