package com.mrebollob.loteria.android.presentation.home

import com.mrebollob.loteria.android.presentation.platform.ErrorMessage

data class HomeUiState(
    val tickets: List<String>,
    val isLoading: Boolean,
    val errorMessages: List<ErrorMessage>,
)
