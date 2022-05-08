package com.mrebollob.loteria.android.presentation.home.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mrebollob.loteria.android.presentation.home.HomeUiState
import com.mrebollob.loteria.android.presentation.home.HomeViewModel
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.Ticket
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = getViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    uiState: HomeUiState
) {
    Surface(
        modifier = Modifier.supportWideScreen()
    ) {
        BaseScaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            snackbarHost = { LotterySnackbarHost(hostState = it) },
            content = {
                LazyColumn(
                    state = rememberLazyListState()
                ) {
                    uiState.tickets.forEach { ticket ->
                        item {
                            Text(
                                text = ticket.name,
                                style = MaterialTheme.typography.body1,
                            )
                        }
                    }
                }
            }
        )
    }
}

@Preview("Home screen")
@Composable
fun PreviewCreateScreen() {
    val uiState = HomeUiState(
        tickets = listOf(
            Ticket(name = "Test ticket 1", number = 0, bet = 20),
            Ticket(name = "Test ticket 2", number = 0, bet = 20)
        ),
        isLoading = false,
        errorMessages = emptyList(),
    )

    LotteryTheme {
        HomeScreen(
            uiState = uiState
        )
    }
}
