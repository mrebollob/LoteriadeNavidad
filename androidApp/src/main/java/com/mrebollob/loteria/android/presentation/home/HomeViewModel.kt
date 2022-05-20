package com.mrebollob.loteria.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.usecase.draw.GetDaysToLotteryDraw
import com.mrebollob.loteria.domain.usecase.settings.GetSortingMethod
import com.mrebollob.loteria.domain.usecase.ticket.GetTickets
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDaysToLotteryDraw: GetDaysToLotteryDraw,
    private val getTickets: GetTickets,
    private val getSortingMethod: GetSortingMethod
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState())
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun refreshData() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val daysToLotteryDraw = getDaysToLotteryDraw.execute()
            val tickets: List<Ticket> = getTickets.execute().getOrElse { emptyList() }
            val sortingMethod =
                getSortingMethod.execute().getOrElse { SortingMethod.NAME }

            viewModelState.update {
                it.copy(
                    daysToLotteryDraw = daysToLotteryDraw,
                    sortingMethod = sortingMethod,
                    tickets = tickets,
                    isLoading = false
                )
            }
        }
    }
}
