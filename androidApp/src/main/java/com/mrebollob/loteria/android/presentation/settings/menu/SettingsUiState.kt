package com.mrebollob.loteria.android.presentation.settings.menu

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.SortingMethod

data class SettingsUiState(
    val isLoading: Boolean,
    val settings: List<SettingsItem>,
    val sortingMethod: SortingMethod,
    val appVersion: String,
    val errorMessages: List<ErrorMessage>,
)

data class SettingsItem(
    val id: SettingItemId,
    @StringRes val title: Int,
    val icon: ImageVector,
    val value: String? = null
)

enum class SettingItemId {
    PRIVACY,
    TERMS,
    EMAIL_ME,
    SHARE,
    MANAGE_TICKETS
}
