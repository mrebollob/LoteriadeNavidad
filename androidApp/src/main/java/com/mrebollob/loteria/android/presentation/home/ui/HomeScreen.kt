package com.mrebollob.loteria.android.presentation.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.home.HomeUiState
import com.mrebollob.loteria.android.presentation.home.HomeViewModel
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.Ticket
import java.util.Date

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onCreateTicketClick: (() -> Unit),
    onSettingsClick: (() -> Unit)
) {
    val uiState by homeViewModel.uiState.collectAsState()

    HomeScreen(
        uiState = uiState,
        onSortTicketClick = {},
        onCreateTicketClick = onCreateTicketClick,
        onSettingsClick = onSettingsClick
    )
}

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    uiState: HomeUiState,
    onSortTicketClick: (() -> Unit),
    onCreateTicketClick: (() -> Unit),
    onSettingsClick: (() -> Unit)
) {
    BaseScaffold(
        modifier = Modifier.supportWideScreen(),
        scaffoldState = scaffoldState,
        toolbarText = stringResource(id = R.string.app_name_sort),
        snackbarHost = { LotterySnackbarHost(hostState = it) },
        barActions = {
            IconButton(
                onClick = {
                    onSortTicketClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Sort,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(R.string.home_screen_menu_sort)
                )
            }

            IconButton(
                onClick = {
                    onCreateTicketClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(R.string.home_screen_menu_new_ticket)
                )
            }

            IconButton(
                onClick = {
                    onSettingsClick()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(R.string.home_screen_menu_settings)
                )
            }
        },
        content = {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
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

                item {
                    StatsSectionView(
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                        totalBet = uiState.totalBet,
                        totalPrize = uiState.totalPrize
                    )
                }

                uiState.tickets.forEach { ticket ->
                    item {
                        TicketItemView(
                            ticket = ticket,
                            totalPrize = 20000000f
                        )
                    }
                }
            }
        }
    )
}

@Preview("Home screen")
@Preview("Home screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeScreen() {
    val uiState = HomeUiState(
        today = Date(),
        daysToLotteryDraw = 10,
        totalBet = 20.5f,
        totalPrize = null,
        tickets = listOf(
            Ticket(name = "Test ticket 1", number = 0, bet = 20f),
            Ticket(name = "Test ticket 2", number = 0, bet = 20f)
        ),
        isLoading = false,
        errorMessages = emptyList(),
    )

    LotteryTheme {
        HomeScreen(
            uiState = uiState,
            onSortTicketClick = {},
            onCreateTicketClick = {},
            onSettingsClick = {}
        )
    }
}
