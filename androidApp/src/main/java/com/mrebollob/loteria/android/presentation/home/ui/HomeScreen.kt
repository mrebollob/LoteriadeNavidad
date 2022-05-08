package com.mrebollob.loteria.android.presentation.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

@Composable
fun HomeScreen(

) {
    Surface() {
        Column() {
            Text(text = "Hello!")
        }
    }
}

@Preview("Home screen")
@Composable
fun PreviewCreateScreen() {
    LotteryTheme {
        HomeScreen(

        )
    }
}
