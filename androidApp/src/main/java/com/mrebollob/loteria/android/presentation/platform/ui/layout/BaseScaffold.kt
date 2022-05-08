package com.mrebollob.loteria.android.presentation.platform.ui.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseScaffold(
    modifier: Modifier = Modifier,
    toolbarText: String = "",
    backgroundColor: Color = MaterialTheme.colors.background,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable () -> Unit,
    onBackClick: (() -> Unit)? = null
) {
    ProvideWindowInsets {
        Scaffold(
            modifier = modifier
                .statusBarsPadding()
                .navigationBarsWithImePadding(),
            scaffoldState = scaffoldState,
            snackbarHost = snackbarHost,
            backgroundColor = backgroundColor,
            topBar = {
                BaseTopAppBar(
                    toolbarText = toolbarText,
                    onBackClick = onBackClick
                )
            },
            content = {
                content()
            }
        )
    }
}

@Preview("BaseScaffold")
@Preview("BaseScaffold (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("BaseScaffold (big font)", fontScale = 1.5f)
@Composable
fun PreviewBaseScaffold() {
    MaterialTheme {
        BaseScaffold(
            content = {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "BaseScaffold")
                }
            },
            onBackClick = {}
        )
    }
}
