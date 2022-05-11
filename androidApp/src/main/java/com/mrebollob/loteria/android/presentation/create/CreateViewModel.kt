package com.mrebollob.loteria.android.presentation.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.domain.repository.TicketsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CreateViewModel(
    private val ticketsRepository: TicketsRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(CreateViewModelState())
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onNameValueChange(name: String) {
        viewModelState.update { it.copy(name = name) }
    }

    fun onNumberValueChange(number: String) {
        viewModelState.update { it.copy(number = number) }
    }

    fun onBetValueChange(bet: String) {
        viewModelState.update { it.copy(bet = bet) }
    }

    fun onSaveTicketClick() {
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {

        }
    }

    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }
}
