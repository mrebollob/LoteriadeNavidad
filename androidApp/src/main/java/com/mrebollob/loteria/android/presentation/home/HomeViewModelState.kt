package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage

data class HomeViewModelState(
    val tickets: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    fun toUiState(): HomeUiState {
        return HomeUiState(
            tickets = tickets,
            isLoading = isLoading,
            errorMessages = errorMessages
        )
    }
}
