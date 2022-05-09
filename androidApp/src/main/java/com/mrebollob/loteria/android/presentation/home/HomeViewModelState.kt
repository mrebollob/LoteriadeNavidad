package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.Ticket
import java.util.Date

data class HomeViewModelState(
    val today: Date = Date(),
    val daysToLotteryDraw: Int = 0,
    val tickets: List<Ticket> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    fun toUiState(): HomeUiState {
        return HomeUiState(
            today = today,
            daysToLotteryDraw = daysToLotteryDraw,
            tickets = tickets,
            isLoading = isLoading,
            errorMessages = errorMessages
        )
    }
}
