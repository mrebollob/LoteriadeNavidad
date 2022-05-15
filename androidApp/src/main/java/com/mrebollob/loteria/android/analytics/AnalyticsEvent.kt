package com.mrebollob.loteria.android.analytics

enum class AnalyticsEvent(val key: String) {

    // Home screen
    NAVIGATE_TO_TICKET_FORM_CLICK("navigate_to_ticket_form_click"),
    NAVIGATE_TO_SETTINGS_CLICK("navigate_to_settings_click"),

    // Ticket form
    SAVE_NEW_TICKET_CLICK("save_new_ticket_click"),

    // Settings screen
    UPDATE_TICKETS_SORTING_METHOD("update_tickets_sorting_method"),
}
