package com.mrebollob.loteria.android.presentation.create

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage

data class CreateViewModelState(
    val name: String = "",
    val number: String = "",
    val bet: String = "",
    val isLoading: Boolean = false,
    val errorMessages: List<ErrorMessage> = emptyList(),
) {

    fun toUiState(): CreateUiState {
        return CreateUiState(
            name = name,
            number = number,
            bet = bet,
            isValidForm = true,
            isLoading = isLoading,
            errorMessages = errorMessages
        )
    }
}
