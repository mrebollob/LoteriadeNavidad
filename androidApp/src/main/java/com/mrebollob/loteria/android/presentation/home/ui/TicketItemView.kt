package com.mrebollob.loteria.android.presentation.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.presentation.platform.ui.components.TicketShape
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey4
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.Ticket

@Composable
fun TicketItemView(
    modifier: Modifier = Modifier,
    ticket: Ticket
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                shadowElevation = 2.dp.toPx()
                shape = TicketShape(16.dp.toPx())
                clip = true
            }
            .padding(start = 32.dp, top = 64.dp, end = 32.dp, bottom = 64.dp),
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                color = MaterialTheme.colors.onSurface,
                text = ticket.name,
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                color = Grey4,
                text = ticket.number.toString(),
                maxLines = 1,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview("Ticket item view")
@Preview("Ticket item view (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewTicketItemView() {
    LotteryTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                TicketItemView(
                    ticket = Ticket(name = "Test ticket 1", number = 0, bet = 0.5f)
                )
                TicketItemView(
                    ticket = Ticket(name = "Test ticket 2", number = 99999, bet = 200f)
                )
            }
        }
    }
}
