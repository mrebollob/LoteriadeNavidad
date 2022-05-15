package com.mrebollob.loteria.android.presentation.settings.manageticket.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.home.ui.HomeEmptyView
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.layout.LoadingContent
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey2
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.settings.manageticket.ManageTicketsUiState
import com.mrebollob.loteria.android.presentation.settings.manageticket.ManageTicketsViewModel
import com.mrebollob.loteria.domain.entity.Ticket

@Composable
fun ManageTicketsScreen(
    manageTicketsViewModel: ManageTicketsViewModel,
    onCreateTicketClick: (() -> Unit),
    navController: NavController
) {
    val uiState by manageTicketsViewModel.uiState.collectAsState()

    ManageTicketsScreen(
        uiState = uiState,
        onDeleteTicketClick = {
            manageTicketsViewModel.onDeleteTicketClick(it)
        },
        onBackClick = { navController.popBackStack() },
        onRefreshData = { manageTicketsViewModel.refreshData() },
        onCreateTicketClick = onCreateTicketClick,
        onErrorDismiss = { manageTicketsViewModel.errorShown(it) },
    )
}

@Composable
fun ManageTicketsScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    uiState: ManageTicketsUiState,
    onDeleteTicketClick: ((Ticket) -> Unit),
    onBackClick: (() -> Unit),
    onRefreshData: () -> Unit,
    onCreateTicketClick: (() -> Unit),
    onErrorDismiss: (Long) -> Unit,
) {
    Surface(
        modifier = Modifier.supportWideScreen(),
    ) {
        BaseScaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            snackbarHost = { LotterySnackbarHost(hostState = it) },
            toolbarText = stringResource(id = R.string.manage_tickets_screen_title),
            onBackClick = onBackClick,
            content = {
                LoadingContent(
                    empty = uiState.tickets.isEmpty(),
                    emptyContent = {
                        HomeEmptyView(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                            onCreateTicketClick = onCreateTicketClick
                        )
                    },
                    loading = uiState.isLoading,
                    onRefresh = onRefreshData,
                    content = {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(4.dp)
                        ) {
                            item {
                                Divider(color = Grey2)
                            }

                            uiState.tickets.forEach { ticket ->
                                item {
                                    ManageTicketItemView(
                                        modifier = Modifier.padding(
                                            start = 24.dp,
                                            end = 24.dp
                                        ),
                                        title = ticket.name,
                                        subTitle = stringResource(
                                            id = R.string.lottery_ticket_number_format,
                                            ticket.number
                                        ) + " - " + stringResource(
                                            id = R.string.lottery_stats_money_format,
                                            ticket.bet
                                        ),
                                        onClick = { onDeleteTicketClick(ticket) }
                                    )
                                    Divider(color = Grey2)
                                }
                            }

                            item {
                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }
                    }
                )
            }
        )

        if (uiState.errorMessages.isNotEmpty()) {
            val errorMessage = remember(uiState) { uiState.errorMessages[0] }

            val errorMessageText: String = stringResource(errorMessage.messageId)
            val retryMessageText = stringResource(id = R.string.retry)

            val onRefreshDataState by rememberUpdatedState(onRefreshData)
            val onErrorDismissState by rememberUpdatedState(onErrorDismiss)

            LaunchedEffect(errorMessageText, retryMessageText, scaffoldState) {
                val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = errorMessageText,
                    actionLabel = retryMessageText
                )
                if (snackbarResult == SnackbarResult.ActionPerformed) {
                    onRefreshDataState()
                }
                onErrorDismissState(errorMessage.id)
            }
        }
    }
}

@Preview("Manage tickets screen")
@Preview("Manage tickets screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewManageTicketsScreen() {
    val uiState = ManageTicketsUiState(
        tickets = emptyList(),
        isLoading = false,
        errorMessages = emptyList(),
    )

    LotteryTheme {
        ManageTicketsScreen(
            uiState = uiState,
            onDeleteTicketClick = {},
            onBackClick = {},
            onRefreshData = {},
            onCreateTicketClick = {},
            onErrorDismiss = {},
        )
    }
}
