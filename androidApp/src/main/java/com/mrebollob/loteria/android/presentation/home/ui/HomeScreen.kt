package com.mrebollob.loteria.android.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.presentation.home.HomeUiState
import com.mrebollob.loteria.android.presentation.home.HomeViewModel
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.Ticket
import java.util.Date
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
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    uiState: HomeUiState
) {
    BaseScaffold(
        modifier = Modifier.supportWideScreen(),
        scaffoldState = scaffoldState,
        snackbarHost = { LotterySnackbarHost(hostState = it) },
        content = {
            LazyColumn(
                state = rememberLazyListState(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    CountdownSectionView(
                        modifier = Modifier.padding(top = 16.dp),
                        today = uiState.today,
                        daysToLotteryDraw = uiState.daysToLotteryDraw
                    )
                }

                uiState.tickets.forEach { ticket ->
                    item {
                        TicketItemView(
                            ticket = ticket
                        )
                    }
                }
            }
        }
    )
}

@Preview("Home screen")
@Composable
fun PreviewCreateScreen() {
    val uiState = HomeUiState(
        today = Date(),
        daysToLotteryDraw = 10,
        tickets = listOf(
            Ticket(name = "Test ticket 1", number = 0, bet = 20f),
            Ticket(name = "Test ticket 2", number = 0, bet = 20f)
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
