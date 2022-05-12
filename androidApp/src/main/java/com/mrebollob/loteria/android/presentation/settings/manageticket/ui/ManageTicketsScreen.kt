package com.mrebollob.loteria.android.presentation.settings.manageticket.ui

import android.content.res.Configuration
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
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.layout.FullScreenLoading
import com.mrebollob.loteria.android.presentation.platform.ui.layout.LoadingContent
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey2
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.settings.manageticket.ManageTicketsUiState
import com.mrebollob.loteria.android.presentation.settings.manageticket.ManageTicketsViewModel
import com.mrebollob.loteria.domain.entity.Ticket
import org.koin.androidx.compose.getViewModel

@Composable
fun ManageTicketsScreen(
    manageTimersViewModel: ManageTicketsViewModel = getViewModel(),
    navController: NavController
) {
    val uiState by manageTimersViewModel.uiState.collectAsState()

    ManageTicketsScreen(
        uiState = uiState,
        onDeleteTicketClick = {
            manageTimersViewModel.onDeleteTicketClick(it)
        },
        onBackClick = { navController.popBackStack() },
        onRefreshData = { manageTimersViewModel.refreshData() },
        onErrorDismiss = { manageTimersViewModel.errorShown(it) },
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
                    empty = uiState.isLoading,
                    emptyContent = { FullScreenLoading() },
                    loading = uiState.isLoading,
                    onRefresh = onRefreshData,
                    content = {
                        LazyColumn(
                            modifier = Modifier.padding(4.dp)
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
                                        subTitle = ticket.number.toString(),
                                        onClick = { onDeleteTicketClick(ticket) }
                                    )
                                    Divider(color = Grey2)
                                }
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
            onErrorDismiss = {},
        )
    }
}
