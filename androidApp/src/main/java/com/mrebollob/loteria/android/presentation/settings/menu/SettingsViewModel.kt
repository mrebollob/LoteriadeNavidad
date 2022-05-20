package com.mrebollob.loteria.android.presentation.settings.menu

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Share
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.android.BuildConfig
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.usecase.settings.GetSortingMethod
import com.mrebollob.loteria.domain.usecase.settings.SaveSortingMethod
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val getSortingMethod: GetSortingMethod,
    private val saveSortingMethod: SaveSortingMethod
) : ViewModel() {

    private val viewModelState = MutableStateFlow(SettingsViewModelState())

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            getSortingMethod.execute()
                .onSuccess { sortingMethod ->
                    viewModelState.update {
                        it.copy(
                            sortingMethod = sortingMethod,
                            settings = getSettings(),
                            appVersion = "v${BuildConfig.VERSION_NAME}",
                            isLoading = false
                        )
                    }
                }.onFailure {
                    showError(R.string.load_error)
                }
        }
    }

    fun setTicketSortingMethod(method: SortingMethod) {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            saveSortingMethod.execute(method)
                .onSuccess {
                    refreshData()
                }.onFailure {
                    showError(R.string.load_error)
                }
        }
    }

    private fun showError(@StringRes messageId: Int) {
        viewModelState.update {
            val errorMessages = it.errorMessages + ErrorMessage(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageId
            )
            it.copy(
                isLoading = false,
                errorMessages = errorMessages
            )
        }
    }


    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }

    companion object {
        fun getSettings() = listOf(
            SettingsItem(
                id = SettingItemId.MANAGE_TICKETS,
                title = R.string.settings_screen_item_manage_tickets,
                icon = Icons.Default.Dehaze,
            ),
            SettingsItem(
                id = SettingItemId.SHARE,
                title = R.string.share_screen_title,
                icon = Icons.Outlined.Share
            ),
            SettingsItem(
                id = SettingItemId.EMAIL_ME,
                title = R.string.settings_screen_item_email_me,
                icon = Icons.Outlined.Email
            ),
            SettingsItem(
                id = SettingItemId.PRIVACY,
                title = R.string.settings_screen_item_privacy,
                icon = Icons.Outlined.Description
            ),
            SettingsItem(
                id = SettingItemId.TERMS,
                title = R.string.settings_screen_item_terms,
                icon = Icons.Outlined.Description
            )
        )
    }
}
