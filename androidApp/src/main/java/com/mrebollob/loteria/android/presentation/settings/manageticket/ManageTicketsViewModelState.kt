package com.mrebollob.loteria.android.presentation.settings.manageticket

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.Ticket

data class ManageTicketsViewModelState(
    val tickets: List<Ticket> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList()
) {

    fun toUiState() = ManageTicketsUiState(
        tickets = tickets,
        isLoading = isLoading,
        errorMessages = errorMessages
    )
}
