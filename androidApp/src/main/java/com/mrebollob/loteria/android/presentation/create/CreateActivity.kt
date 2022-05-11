package com.mrebollob.loteria.android.presentation.create

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mrebollob.loteria.android.presentation.create.ui.CreateScreen
import com.mrebollob.loteria.android.presentation.platform.BaseActivity
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

class CreateActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

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
                    onBackClick = { onBackPressed() }
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent = Intent(context, CreateActivity::class.java)
    }
}
