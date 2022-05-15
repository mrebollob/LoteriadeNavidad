package com.mrebollob.loteria.android.presentation.settings.manageticket.ui

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.platform.ui.theme.SystemRed

@Composable
fun ManageTicketItemView(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String = "",
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onSurface,
                )

                Text(
                    modifier = Modifier
                        .padding(start = 8.dp),
                    color = MaterialTheme.colors.onSurface.copy(0.7f),
                    text = subTitle,
                    style = MaterialTheme.typography.body2
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .clickable { onClick() }
                    .size(24.dp),
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(id = R.string.manage_tickets_screen_delete_action),
                tint = SystemRed
            )
        }
    }
}

@Preview("Manage ticket item")
@Preview("Manage ticket item (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewManageTicketItemView() {
    LotteryTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                ManageTicketItemView(
                    title = "Familia",
                    subTitle = "00000 - 20 €",
                    onClick = {}
                )

                ManageTicketItemView(
                    title = "Amigos",
                    subTitle = "99999 - 200 €",
                    onClick = {}
                )
            }
        }
    }
}
