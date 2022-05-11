package com.mrebollob.loteria.android.presentation.create.ui

import android.content.res.Configuration
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.create.CreateUiState
import com.mrebollob.loteria.android.presentation.create.CreateViewModel
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.components.OutlinedBoxTextField
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

@Composable
fun CreateScreen(
    createViewModel: CreateViewModel,
    onBackClick: (() -> Unit)
) {
    val uiState by createViewModel.uiState.collectAsState()

    CreateScreen(
        uiState = uiState,
        onNameValueChange = { createViewModel.onNameValueChange(it) },
        onNumberValueChange = { createViewModel.onNumberValueChange(it) },
        onBetValueChange = { createViewModel.onBetValueChange(it) },
        onSaveTicketClick = { createViewModel.onSaveTicketClick() },
        onErrorDismiss = { createViewModel.errorShown(it) },
        onBackClick = onBackClick
    )
}

@Composable
fun CreateScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    uiState: CreateUiState,
    onNameValueChange: (String) -> Unit,
    onNumberValueChange: (String) -> Unit,
    onBetValueChange: (String) -> Unit,
    onSaveTicketClick: (() -> Unit),
    onErrorDismiss: (Long) -> Unit,
    onBackClick: (() -> Unit)
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BaseScaffold(
        modifier = Modifier.supportWideScreen(),
        scaffoldState = scaffoldState,
        toolbarText = stringResource(id = R.string.create_screen_title),
        snackbarHost = { LotterySnackbarHost(hostState = it) },
        onBackClick = onBackClick,
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                OutlinedBoxTextField(
                    textFieldModifier = Modifier
                        .focusRequester(focusRequester)
                        .focusable(),
                    label = stringResource(R.string.create_screen_ticket_name),
                    value = uiState.name,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text,
                        autoCorrect = true,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    onValueChange = onNameValueChange,
                )

                OutlinedBoxTextField(
                    modifier = Modifier.padding(top = 16.dp),
                    label = stringResource(R.string.create_screen_ticket_number),
                    value = uiState.number,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number,
                        autoCorrect = false,
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    onValueChange = onNumberValueChange,
                )

                OutlinedBoxTextField(
                    modifier = Modifier.padding(top = 16.dp),
                    label = stringResource(R.string.create_screen_ticket_bet),
                    value = uiState.bet,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number,
                        autoCorrect = false,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            if (uiState.isValidForm) {
                                onSaveTicketClick()
                            }
                        }
                    ),
                    onValueChange = onBetValueChange,
                )

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clipToBounds()
                        .padding(top = 32.dp),
                    shape = RoundedCornerShape(24.dp),
                    onClick = {
                        focusManager.clearFocus()
                        onSaveTicketClick()
                    },
                    enabled = uiState.isValidForm
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                        text = stringResource(R.string.create_screen_ticket_save),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }
    )

    if (uiState.errorMessages.isNotEmpty()) {
        val errorMessage = remember(uiState) { uiState.errorMessages[0] }

        val errorMessageText: String = stringResource(errorMessage.messageId)
        val retryMessageText = stringResource(id = R.string.ok_text)
        val onErrorDismissState by rememberUpdatedState(onErrorDismiss)

        LaunchedEffect(errorMessageText, retryMessageText, scaffoldState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessageText,
                actionLabel = retryMessageText
            )
            onErrorDismissState(errorMessage.id)
        }
    }
}

@Preview("Create screen")
@Preview("Create screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCreateScreen() {
    val uiState = CreateUiState(
        name = "Familia",
        number = "0",
        bet = "20.5",
        isValidForm = true,
        isLoading = false,
        errorMessages = emptyList(),
    )

    LotteryTheme {
        CreateScreen(
            uiState = uiState,
            onNameValueChange = {},
            onNumberValueChange = {},
            onBetValueChange = {},
            onSaveTicketClick = {},
            onErrorDismiss = {},
            onBackClick = {}
        )
    }
}
