package com.mrebollob.loteria.android.presentation.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey4
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

@Composable
fun StatsSectionView(
    modifier: Modifier = Modifier,
    totalBet: Float,
    totalPrize: Float?
) {
    Column(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        color = Grey4,
                        text = stringResource(id = R.string.lottery_stats_total_bet),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.subtitle2
                    )

                    Text(
                        text = stringResource(id = R.string.lottery_stats_money_format, totalBet),
                        style = MaterialTheme.typography.h6,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        color = Grey4,
                        text = stringResource(id = R.string.lottery_stats_total_prize),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.subtitle2
                    )

                    Text(
                        text = if (totalPrize != null) {
                            stringResource(id = R.string.lottery_stats_money_format, totalPrize)
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

@Preview("Stats section")
@Preview("Stats section (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewStatsSectionView() {
    LotteryTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                StatsSectionView(
                    totalBet = 20.5f,
                    totalPrize = null
                )
            }
        }
    }
}
