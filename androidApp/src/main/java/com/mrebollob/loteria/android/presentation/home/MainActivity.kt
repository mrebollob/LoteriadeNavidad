package com.mrebollob.loteria.android.presentation.home

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.mrebollob.loteria.Greeting
import com.mrebollob.loteria.android.presentation.home.ui.HomeScreen
import com.mrebollob.loteria.android.presentation.platform.BaseActivity
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : BaseActivity() {

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

                HomeScreen(

                )
            }
        }
    }
}
