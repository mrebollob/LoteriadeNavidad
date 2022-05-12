package com.mrebollob.loteria.android.presentation.settings.manageticket

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.Ticket

data class ManageTicketsUiState(
    val tickets: List<Ticket>,
    val isLoading: Boolean,
    val errorMessages: List<ErrorMessage>,
)
