package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.entity.Ticket
import java.util.Date

data class HomeUiState(
    val today: Date,
    val daysToLotteryDraw: Int,
    val totalBet: Float,
    val totalPrize: Float?,
    val tickets: List<Ticket>,
    val isLoading: Boolean,
    val errorMessages: List<ErrorMessage>,
)
