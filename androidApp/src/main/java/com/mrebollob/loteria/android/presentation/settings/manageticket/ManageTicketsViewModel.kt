package com.mrebollob.loteria.android.presentation.settings.manageticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.domain.entity.Ticket
import com.mrebollob.loteria.domain.usecase.ticket.DeleteTicket
import com.mrebollob.loteria.domain.usecase.ticket.GetTickets
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ManageTicketsViewModel(
    private val getTickets: GetTickets,
    private val deleteTicket: DeleteTicket
) : ViewModel() {

    private val viewModelState = MutableStateFlow(ManageTicketsViewModelState())

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

    fun refreshData() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val tickets = getTickets.execute()
            viewModelState.update {
                it.copy(
                    tickets = tickets,
                    isLoading = false
                )
            }
        }
    }

    fun onDeleteTicketClick(ticket: Ticket) {
        viewModelScope.launch {
            deleteTicket.execute(ticket)
            refreshData()
        }
    }

    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }
}
