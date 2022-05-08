package com.mrebollob.loteria.android.presentation.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

@Composable
fun HomeScreen(

) {
    HomeScreen(
        text = "Hello",
    )
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    text: String
) {
    Surface(
        modifier = Modifier.supportWideScreen()
    ) {
        BaseScaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            snackbarHost = { LotterySnackbarHost(hostState = it) },
            content = {
                Column {
                    Text(text = text)
                }
            }
        )
    }
}

@Preview("Home screen")
@Composable
fun PreviewCreateScreen() {
    LotteryTheme {
        HomeScreen(
            text = "Hello",
        )
    }
}
