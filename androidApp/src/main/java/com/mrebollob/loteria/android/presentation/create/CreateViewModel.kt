package com.mrebollob.loteria.android.presentation.create

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.ErrorMessage
import com.mrebollob.loteria.domain.usecase.ticket.CreateTicket
import java.util.UUID
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CreateViewModel(
    private val createTicket: CreateTicket
) : ViewModel() {

    val onTicketCreated = Channel<Unit>(Channel.CONFLATED)
    private val viewModelState = MutableStateFlow(CreateViewModelState())
    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    fun onNameValueChange(name: String) {
        if (name.length <= 20) {
            viewModelState.update { it.copy(name = name) }
        }
    }

    fun onNumberValueChange(number: String) {
        if (number.length <= 5) {
            viewModelState.update { it.copy(number = number) }
        }
    }

    fun onBetValueChange(bet: String) {
        if (bet.length <= 6) {
            viewModelState.update { it.copy(bet = bet) }
        }
    }

    fun onSaveTicketClick() {
        viewModelState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val number = viewModelState.value.number.toIntOrNull()
            val bet = viewModelState.value.bet.replace(",", ".").toFloatOrNull()
            if (number != null && bet != null && bet > 0) {
                createTicket.execute(
                    name = viewModelState.value.name,
                    number = number,
                    bet = bet
                )
                onTicketCreated.trySend(Unit)
            } else {
                viewModelState.update { it.copy(isLoading = false) }
                delay(200)
                if (number == null) {
                    showError(R.string.create_screen_error_number)
                } else if (bet == null || bet <= 0) {
                    showError(R.string.create_screen_error_bet)
                }
            }
        }
    }

    private fun showError(@StringRes messageId: Int) {
        viewModelState.update {
            val errorMessages = it.errorMessages + ErrorMessage(
                id = UUID.randomUUID().mostSignificantBits,
                messageId = messageId
            )
            it.copy(
                isLoading = false,
                errorMessages = errorMessages
            )
        }
    }

    fun errorShown(errorId: Long) {
        viewModelState.update { currentUiState ->
            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
            currentUiState.copy(errorMessages = errorMessages)
        }
    }
}
