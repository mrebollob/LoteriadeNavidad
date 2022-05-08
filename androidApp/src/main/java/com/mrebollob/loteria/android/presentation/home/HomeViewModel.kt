package com.mrebollob.loteria.android.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel() : ViewModel() {

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
            val tickets = listOf("Ticket 1", "Ticket 2")

            viewModelState.update {
                it.copy(
                    tickets = tickets,
                    isLoading = false
                )
            }
        }
    }
}
