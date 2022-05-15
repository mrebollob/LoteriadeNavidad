package com.mrebollob.loteria.android.presentation.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.Ticket

@Composable
fun HomeEmptyView(
    modifier: Modifier = Modifier,
    onCreateTicketClick: (() -> Unit),
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            modifier = Modifier.padding(top = 72.dp),
            text = stringResource(id = R.string.home_screen_empty_title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h5
        )

        Box(
            modifier = Modifier.padding(top = 32.dp, start = 32.dp, end = 32.dp)
        ) {
            TicketItemView(
                ticket = Ticket(
                    id = 1,
                    name = stringResource(id = R.string.home_screen_empty_ticket_sample_name),
                    number = 0,
                    bet = 2.5f
                ),
                totalPrize = null
            )
        }

        Button(
            modifier = Modifier
                .clipToBounds()
                .padding(top = 56.dp),
            shape = RoundedCornerShape(24.dp),
            onClick = onCreateTicketClick
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = MaterialTheme.colors.onPrimary,
                contentDescription = stringResource(R.string.home_screen_empty_cta)
            )

            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                text = stringResource(R.string.home_screen_empty_cta),
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Preview("Home empty view")
@Preview("Home empty view (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewHomeEmptyView() {
    LotteryTheme {
        HomeEmptyView(
            onCreateTicketClick = {}
        )
    }
}
