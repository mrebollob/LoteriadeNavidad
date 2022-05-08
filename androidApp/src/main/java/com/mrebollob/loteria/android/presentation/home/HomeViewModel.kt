package com.mrebollob.loteria.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(
    private val ticketsRepository: TicketsRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState())
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val tickets = ticketsRepository.getTickets().getOrElse { emptyList() }
            viewModelState.update {
                it.copy(
                    tickets = tickets,
                    isLoading = false
                )
            }
        }
    }
}
