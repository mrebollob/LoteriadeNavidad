package com.mrebollob.loteria.android.presentation.settings.menu.ui

import android.content.res.Configuration
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.layout.FullScreenLoading
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey2
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey4
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.settings.menu.SettingItemId
import com.mrebollob.loteria.android.presentation.settings.menu.SettingsUiState
import com.mrebollob.loteria.android.presentation.settings.menu.SettingsViewModel
import com.mrebollob.loteria.domain.entity.SortingMethod
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel = getViewModel(),
    onSettingClick: (id: SettingItemId) -> Unit,
    onBackClick: (() -> Unit)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by settingsViewModel.uiState.collectAsState()
    val modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    SettingsScreen(
        uiState = uiState,
        onErrorDismiss = { settingsViewModel.errorShown(it) },
        onRefreshData = { settingsViewModel.refreshData() },
        onSettingClick = {

            onSettingClick(it)
        },
        onBackClick = onBackClick,
        onSetTicketsSortClick = {
            coroutineScope.launch {
                modalBottomSheetState.show()
            }
        },
        onSortingMethodSelected = { method ->
            settingsViewModel.setTicketSortingMethod(method)
            coroutineScope.launch {
                modalBottomSheetState.hide()
            }
        },
        modalBottomSheetState = modalBottomSheetState
    )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun SettingsScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    modalBottomSheetState: ModalBottomSheetState,
    uiState: SettingsUiState,
    onErrorDismiss: (Long) -> Unit,
    onRefreshData: () -> Unit,
    onSettingClick: (id: SettingItemId) -> Unit,
    onSetTicketsSortClick: (() -> Unit),
    onBackClick: (() -> Unit),
    onSortingMethodSelected: (SortingMethod) -> Unit,
) {
    BaseScaffold(
        modifier = Modifier.supportWideScreen(),
        scaffoldState = scaffoldState,
        snackbarHost = { LotterySnackbarHost(hostState = it) },
        toolbarText = stringResource(id = R.string.settings_screen_title),
        onBackClick = onBackClick,
        content = {
            if (uiState.isLoading) {
                FullScreenLoading()
            } else {
                ModalBottomSheetLayout(
                    sheetState = modalBottomSheetState,
                    sheetContent = {
                        SetTicketsSortingMethodView(
                            currentSortingMethod = uiState.sortingMethod,
                            onSortingMethodSelected = onSortingMethodSelected
                        )
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                    ) {
                        Divider(color = Grey2)

                        SettingsItemView(
                            title = stringResource(R.string.settings_screen_tickets_sorting_method),
                            imageVector = Icons.Default.Sort,
                            value = "test",
                            onClick = onSetTicketsSortClick
                        )

                        Divider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            color = Grey2
                        )

                        uiState.settings.forEach { setting ->
                            SettingsItemView(
                                title = stringResource(id = setting.title),
                                imageVector = setting.icon,
                                value = setting.value,
                                onClick = {
                                    onSettingClick(setting.id)
                                }
                            )

                            Divider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                color = Grey2
                            )
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = Grey4,
                            textAlign = TextAlign.Center,
                            text = uiState.appVersion,
                            style = MaterialTheme.typography.body2
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
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

@OptIn(ExperimentalMaterialApi::class, ExperimentalAnimationApi::class)
@Preview("Settings screen")
@Preview("Settings screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSettingsScreen() {
    LotteryTheme {
        val uiState = SettingsUiState(
            isLoading = false,
            sortingMethod = SortingMethod.NAME,
            settings = SettingsViewModel.getSettings(),
            appVersion = "v1.0.0",
            errorMessages = emptyList()
        )

        val modalBottomSheetState: ModalBottomSheetState = rememberModalBottomSheetState(
            initialValue = ModalBottomSheetValue.Hidden
        )

        SettingsScreen(
            uiState = uiState,
            onErrorDismiss = {},
            onRefreshData = {},
            onSetTicketsSortClick = {},
            onSettingClick = {},
            onBackClick = {},
            onSortingMethodSelected = {},
            modalBottomSheetState = modalBottomSheetState
        )
    }
}
