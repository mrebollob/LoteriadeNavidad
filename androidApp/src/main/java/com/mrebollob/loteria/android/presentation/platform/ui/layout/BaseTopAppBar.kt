package com.mrebollob.loteria.android.presentation.platform.ui.layout

import android.content.res.Configuration
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R

@Composable
fun BaseTopAppBar(
    modifier: Modifier = Modifier,
    toolbarText: String,
    onBackClick: (() -> Unit)? = null,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            if (toolbarText.isNotEmpty()) {
                Text(
                    text = toolbarText,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h5
                )
            }
        },
        actions = {

        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = if (onBackClick != null) {
            {
                IconButton(
                    onClick = {
                        onBackClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        } else {
            null
        }
    )
}

@Preview("BaseTopAppBar")
@Preview("BaseTopAppBar (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("BaseTopAppBar (big font)", fontScale = 1.5f)
@Composable
fun PreviewBaseTopAppBar() {
    MaterialTheme {
        BaseTopAppBar(
            toolbarText = "Test app",
            onBackClick = {}
        )
    }
}
