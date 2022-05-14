package com.mrebollob.loteria.android.presentation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mrebollob.loteria.android.analytics.AnalyticsEvent
import com.mrebollob.loteria.android.analytics.AnalyticsManager
import com.mrebollob.loteria.android.analytics.AnalyticsParameter
import com.mrebollob.loteria.android.analytics.AnalyticsScreen
import com.mrebollob.loteria.android.presentation.create.CreateActivity
import com.mrebollob.loteria.android.presentation.home.ui.HomeScreen
import com.mrebollob.loteria.android.presentation.platform.BaseActivity
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.settings.SettingsActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val analyticsManager: AnalyticsManager by inject()

    private val createTicketResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            homeViewModel.refreshData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            LotteryTheme {
                val systemUiController = rememberSystemUiController()
                val darkIcons = MaterialTheme.colors.isLight
                SideEffect {
                    systemUiController.setStatusBarColor(
                        Color.Transparent,
                        darkIcons = darkIcons
                    )
                }

                HomeScreen(
                    homeViewModel = homeViewModel,
                    onCreateTicketClick = {
                        analyticsManager.trackEvent(
                            AnalyticsEvent.NAVIGATE_TO_TICKET_FORM_CLICK,
                            AnalyticsParameter.CURRENT_LOCATION.withScreenValue(AnalyticsScreen.HOME)
                        )
                        openCreateTicketScreen()
                    },
                    onSettingsClick = {
                        analyticsManager.trackEvent(
                            AnalyticsEvent.NAVIGATE_TO_SETTINGS_CLICK,
                            AnalyticsParameter.CURRENT_LOCATION.withScreenValue(AnalyticsScreen.HOME)
                        )
                        SettingsActivity.open(this)
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.refreshData()
    }

    private fun openCreateTicketScreen() {
        createTicketResult.launch(CreateActivity.newIntent(this))
    }
}
