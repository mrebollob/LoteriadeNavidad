package com.mrebollob.loteria.android.presentation.home.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.extension.getStringDate
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey4
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import java.util.Date

@Composable
fun CountdownSectionView(
    modifier: Modifier = Modifier,
    today: Date,
    daysToLotteryDraw: Int
) {
    val resources = LocalContext.current.resources

    Column(
        modifier = modifier
    ) {
        Text(
            color = Grey4,
            text = today.getStringDate(),
            style = MaterialTheme.typography.body2
        )

        Text(
            text = when {
                daysToLotteryDraw == 0 -> stringResource(R.string.lottery_countdown_today)
                daysToLotteryDraw < 0 -> stringResource(R.string.lottery_countdown_end)
                else -> resources.getQuantityString(
                    R.plurals.lottery_countdown_days,
                    daysToLotteryDraw,
                    daysToLotteryDraw
                )
            },
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Preview("Countdown section")
@Preview("Countdown section (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountdownSectionView() {
    LotteryTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CountdownSectionView(
                    today = Date(),
                    daysToLotteryDraw = 10
                )
            }
        }
    }
}
