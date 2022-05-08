package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.Ticket

data class HomeViewModelState(
    val tickets: List<Ticket> = emptyList(),
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
