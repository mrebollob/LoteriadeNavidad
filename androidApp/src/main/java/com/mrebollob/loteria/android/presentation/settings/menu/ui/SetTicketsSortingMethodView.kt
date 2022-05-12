package com.mrebollob.loteria.android.presentation.settings.menu.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.R
import com.mrebollob.loteria.android.presentation.platform.extension.getNameString
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey7
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.domain.entity.SortingMethod

@ExperimentalAnimationApi
@Composable
fun SetTicketsSortingMethodView(
    modifier: Modifier = Modifier,
    currentSortingMethod: SortingMethod,
    onSortingMethodSelected: (SortingMethod) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedSortingMethod by remember { mutableStateOf(currentSortingMethod) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 24.dp,
                horizontal = 16.dp,
            )
    ) {
        Text(
            text = stringResource(id = R.string.settings_screen_tickets_sorting_method),
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .clickable { expanded = !expanded },
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(selectedSortingMethod.getNameString()),
                style = MaterialTheme.typography.subtitle1
            )
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                tint = MaterialTheme.colors.onSecondary,
                contentDescription = null
            )
        }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            SortingMethod.values().forEach { sortingMethod ->
                DropdownMenuItem(
                    onClick = {
                        selectedSortingMethod = sortingMethod
                        expanded = false
                    }
                ) {
                    Text(
                        text = stringResource(sortingMethod.getNameString()),
                        style = MaterialTheme.typography.subtitle1
                    )
                }
            }
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 36.dp),
            onClick = {
                onSortingMethodSelected(selectedSortingMethod)
            },
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Grey7
            )
        ) {
            Text(
                modifier = modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                text = stringResource(R.string.settings_screen_save_action),
                color = Color.White,
                style = MaterialTheme.typography.subtitle1
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview("Set tickets sorting method view")
@Composable
fun PreviewSetTicketsSortingMethodView() {
    LotteryTheme {
        SetTicketsSortingMethodView(
            currentSortingMethod = SortingMethod.NAME,
            onSortingMethodSelected = {},
        )
    }
}
