package com.mrebollob.loteria.android.presentation.create

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mrebollob.loteria.android.analytics.AnalyticsManager
import com.mrebollob.loteria.android.presentation.create.ui.CreateScreen
import com.mrebollob.loteria.android.presentation.platform.BaseActivity
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import kotlinx.coroutines.flow.consumeAsFlow
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CreateActivity : BaseActivity() {

    private val createViewModel: CreateViewModel by viewModel()

    private val analyticsManager: AnalyticsManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launchWhenCreated {
            createViewModel.onTicketCreated.consumeAsFlow().collect {
                onTicketCreated()
            }
        }

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

                CreateScreen(
                    createViewModel = createViewModel,
                    analyticsManager = analyticsManager,
                    onBackClick = { onBackPressed() }
                )
            }
        }
    }

    private fun onTicketCreated() {
        val data = Intent()
        setResult(Activity.RESULT_OK, data)
        finish()
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, CreateActivity::class.java)
    }
}
