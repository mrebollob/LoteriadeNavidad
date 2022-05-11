package com.mrebollob.loteria.android.presentation.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.ui.components.TicketShape
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey1
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey4
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.Ticket

@Composable
fun TicketItemView(
    modifier: Modifier = Modifier,
    ticket: Ticket,
    totalPrize: Float?
) {
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Grey1,
            elevation = 4.dp,
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .graphicsLayer {
                        shape = TicketShape(16.dp.toPx())
                        clip = true
                    }
                    .background(MaterialTheme.colors.surface)
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .padding(top = 16.dp, end = 16.dp, start = 16.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                color = Grey4,
                                text = stringResource(id = R.string.lottery_ticket_name),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.subtitle2
                            )

                            Text(
                                text = ticket.name,
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text(
                                color = Grey4,
                                text = stringResource(id = R.string.lottery_ticket_number),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.subtitle2
                            )

                            Text(
                                text = stringResource(
                                    id = R.string.lottery_ticket_number_format,
                                    ticket.number
                                ),
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .fillMaxWidth()
                            .background(Grey1)
                            .weight(1f)
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp, top = 8.dp)
                        ) {
                            Text(
                                color = Grey4,
                                text = stringResource(id = R.string.lottery_ticket_bet),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.subtitle2
                            )

                            Text(
                                text = stringResource(
                                    id = R.string.lottery_stats_money_format,
                                    ticket.bet
                                ),
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(end = 16.dp, top = 8.dp),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text(
                                color = Grey4,
                                text = stringResource(id = R.string.lottery_ticket_prize),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.subtitle2
                            )

                            Text(
                                text = if (totalPrize != null) {
                                    stringResource(
                                        id = R.string.lottery_stats_money_format,
                                        totalPrize
                                    )
                                } else {
                                    stringResource(id = R.string.lottery_stats_total_prize_empty)
                                },
                                style = MaterialTheme.typography.h6,
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }
                }
            }
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
                    ticket = Ticket(name = "Test ticket 1", number = 0, bet = 0.5f),
                    totalPrize = null
                )
                TicketItemView(
                    ticket = Ticket(name = "Test ticket 2", number = 99999, bet = 200f),
                    totalPrize = 20000000f
                )
            }
        }
    }
}
