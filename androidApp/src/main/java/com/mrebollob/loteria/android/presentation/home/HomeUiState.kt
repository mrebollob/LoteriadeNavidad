package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.Ticket

data class HomeUiState(
    val tickets: List<Ticket>,
    val isLoading: Boolean,
    val errorMessages: List<ErrorMessage>,
)
