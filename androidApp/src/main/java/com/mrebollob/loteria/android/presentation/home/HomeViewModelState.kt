package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.entity.Ticket
import java.util.Date

data class HomeViewModelState(
    val today: Date = Date(),
    val daysToLotteryDraw: Int = 0,
    val tickets: List<Ticket> = emptyList(),
    val sortingMethod: SortingMethod = SortingMethod.NAME,
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    fun toUiState(): HomeUiState {
        return HomeUiState(
            today = today,
            daysToLotteryDraw = daysToLotteryDraw,
            totalBet = tickets.map { it.bet }.sum(),
            totalPrize = null,
            tickets = when (sortingMethod) {
                SortingMethod.NAME -> tickets.sortedBy { it.name }
                SortingMethod.NUMBER -> tickets.sortedBy { it.number }
            },
            isLoading = isLoading,
            errorMessages = errorMessages
        )
    }
}
