package com.mrebollob.loteria.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.domain.entity.SortingMethod
import com.mrebollob.loteria.domain.repository.SettingsRepository
import com.mrebollob.loteria.domain.repository.TicketsRepository
import com.mrebollob.loteria.domain.usecase.GetDaysToLotteryDraw
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getDaysToLotteryDraw: GetDaysToLotteryDraw,
    private val ticketsRepository: TicketsRepository,
    private val settingsRepository: SettingsRepository,
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
            val tickets = ticketsRepository.getTickets().getOrElse { emptyList() }
            val sortingMethod =
                settingsRepository.getSortingMethod().getOrElse { SortingMethod.NAME }

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
