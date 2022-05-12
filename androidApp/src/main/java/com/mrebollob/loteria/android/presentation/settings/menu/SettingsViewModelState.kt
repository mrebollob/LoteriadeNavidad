package com.mrebollob.loteria.android.presentation.settings.menu

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.SortingMethod

data class SettingsViewModelState(
    val isLoading: Boolean = false,
    val settings: List<SettingsItem> = emptyList(),
    val sortingMethod: SortingMethod = SortingMethod.NAME,
    val appVersion: String = "",
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    fun toUiState() = SettingsUiState(
        isLoading = isLoading,
        settings = settings,
        sortingMethod = sortingMethod,
        appVersion = appVersion,
        errorMessages = errorMessages
    )
}
