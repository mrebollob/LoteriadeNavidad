package com.mrebollob.loteria.android.presentation.settings.menu.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

@Composable
fun SettingsItemView(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    title: String,
    value: String? = null,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = MaterialTheme.colors.background
    ) {
        Row(
            modifier = modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = imageVector,
                contentDescription = title,
                tint = MaterialTheme.colors.onSurface
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    color = MaterialTheme.colors.onSurface,
                    text = title,
                    style = MaterialTheme.typography.subtitle1
                )

                if (value != null) {
                    Text(
                        color = MaterialTheme.colors.onSurface.copy(0.7f),
                        text = value,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Preview("Settings item")
@Preview("Settings item (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewSettingsItemView() {
    LotteryTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                SettingsItemView(
                    imageVector = Icons.Filled.Share,
                    title = "Item 1",
                    onClick = {}
                )

                SettingsItemView(
                    imageVector = Icons.Filled.Share,
                    title = "Item 2",
                    onClick = {}
                )
            }
        }
    }
}
