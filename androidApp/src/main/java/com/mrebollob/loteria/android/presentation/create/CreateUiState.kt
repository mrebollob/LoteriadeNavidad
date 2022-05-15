package com.mrebollob.loteria.android.presentation.create

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage

data class CreateUiState(
    val name: String,
    val number: String,
    val bet: String,
    val isValidForm: Boolean,
    val isLoading: Boolean,
    val errorMessages: List<ErrorMessage>,
)
