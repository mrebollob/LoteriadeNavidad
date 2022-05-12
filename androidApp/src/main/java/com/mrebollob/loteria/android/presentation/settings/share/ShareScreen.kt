package com.mrebollob.loteria.android.presentation.settings.share

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.extension.supportWideScreen
import com.mrebollob.loteria.android.presentation.platform.ui.components.LotterySnackbarHost
import com.mrebollob.loteria.android.presentation.platform.ui.layout.BaseScaffold
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey7
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShareScreen(
    navController: NavController
) {
    val configuration: Configuration = LocalConfiguration.current
    var animPlaying by remember { mutableStateOf(false) }
    val composition: LottieComposition? by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.love_explosion)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = animPlaying
    )
    if (progress == 1f) {
        animPlaying = false
    }

    ShareScreen(
        configuration = configuration,
        composition = composition,
        progress = progress,
        onOpenGooglePlayClick = {
            animPlaying = true
        },
        onShareClick = {
            animPlaying = true
        },
        onBackClick = { navController.popBackStack() }
    )
}

@ExperimentalMaterialApi
@Composable
fun ShareScreen(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    configuration: Configuration,
    composition: LottieComposition?,
    progress: Float,
    onOpenGooglePlayClick: () -> Unit,
    onShareClick: () -> Unit,
    onBackClick: (() -> Unit)
) {
    Surface(
        modifier = Modifier.supportWideScreen()
    ) {
        BaseScaffold(
            modifier = modifier,
            scaffoldState = scaffoldState,
            snackbarHost = { LotterySnackbarHost(hostState = it) },
            toolbarText = stringResource(id = R.string.share_screen_title),
            onBackClick = onBackClick,
            content = {
                if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    LandscapeSection(
                        composition = composition,
                        progress = progress,
                        onOpenGooglePlayClick = onOpenGooglePlayClick,
                        onShareClick = onShareClick
                    )
                } else {
                    PortraitSection(
                        composition = composition,
                        progress = progress,
                        onOpenGooglePlayClick = onOpenGooglePlayClick,
                        onShareClick = onShareClick
                    )
                }
            }
        )
    }
}

@Composable
private fun PortraitSection(
    modifier: Modifier = Modifier,
    composition: LottieComposition?,
    progress: Float,
    onOpenGooglePlayClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp
            )
    ) {
        QrSection(
            modifier = modifier
                .height(200.dp),
            composition = composition,
            progress = progress
        )

        ShareButtonSection(
            onOpenGooglePlayClick = onOpenGooglePlayClick,
            onShareClick = onShareClick
        )
    }
}

@Composable
private fun LandscapeSection(
    modifier: Modifier = Modifier,
    composition: LottieComposition?,
    progress: Float,
    onOpenGooglePlayClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        QrSection(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            composition = composition,
            progress = progress,

            )

        ShareButtonSection(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight(),
            onOpenGooglePlayClick = onOpenGooglePlayClick,
            onShareClick = onShareClick
        )
    }
}

@Composable
private fun QrSection(
    modifier: Modifier = Modifier,
    composition: LottieComposition?,
    progress: Float
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.ic_lottery_qr),
            contentDescription = stringResource(R.string.share_screen_title)
        )
        if (progress < 1f) {
            LottieAnimation(
                modifier = Modifier.size(150.dp),
                composition = composition,
                progress = progress,
            )
        }
    }
}

@Composable
private fun ShareButtonSection(
    modifier: Modifier = Modifier,
    onOpenGooglePlayClick: () -> Unit,
    onShareClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { onOpenGooglePlayClick() },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Grey7
            )
        ) {
            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.share_screen_app_url_label),
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            onClick = { onShareClick() },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Grey7
            )
        ) {
            Icon(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .align(Alignment.CenterVertically),
                imageVector = Icons.Filled.Share,
                tint = Color.White,
                contentDescription = stringResource(R.string.share_screen_title)
            )
            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp, vertical = 6.dp)
                    .align(Alignment.CenterVertically),
                text = stringResource(R.string.share_screen_title),
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview("Share screen")
@Preview("Share screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewShareScreen() {
    LotteryTheme {
        val configuration: Configuration = LocalConfiguration.current
        val composition: LottieComposition? by rememberLottieComposition(
            LottieCompositionSpec.JsonString(
                "love_explosion.json"
            )
        )
        val progress: Float by animateLottieCompositionAsState(composition)

        ShareScreen(
            configuration = configuration,
            composition = composition,
            progress = progress,
            onOpenGooglePlayClick = {},
            onShareClick = {},
            onBackClick = {}
        )
    }
}
