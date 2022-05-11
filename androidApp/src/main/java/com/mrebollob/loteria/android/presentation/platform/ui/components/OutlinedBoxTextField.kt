package com.mrebollob.loteria.android.presentation.platform.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mrebollob.loteria.android.presentation.platform.ui.theme.Grey3
import com.mrebollob.loteria.android.presentation.platform.ui.theme.LotteryTheme
import com.mrebollob.loteria.android.presentation.platform.ui.theme.SystemBlue

@Composable
fun OutlinedBoxTextField(
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    label: String,
    value: String,
    hint: String? = null,
    error: String? = null,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            modifier = textFieldModifier
                .fillMaxWidth(),
            value = value,
            label = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                )
            },
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            singleLine = true,
            readOnly = readOnly,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            onValueChange = { onValueChange(it) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = MaterialTheme.colors.onSurface,
                textColor = MaterialTheme.colors.onSurface,
                focusedBorderColor = SystemBlue,
                unfocusedBorderColor = Grey3,
                errorBorderColor = MaterialTheme.colors.error,
                cursorColor = MaterialTheme.colors.onSurface,
            ),
            maxLines = 1,
            textStyle = MaterialTheme.typography.body1,
            isError = error != null
        )

        if (hint != null) {
            Text(
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 4.dp,
                    end = 4.dp
                ),
                text = hint,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f),
                style = MaterialTheme.typography.body1
            )
        }

        if (error != null) {
            Text(
                modifier = Modifier.padding(
                    top = 4.dp,
                    start = 4.dp,
                    end = 4.dp
                ),
                text = error,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview("OutlinedBoxTextField")
@Preview("OutlinedBoxTextField (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewOutlinedBoxTextField() {
    LotteryTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedBoxTextField(
                    label = "Label",
                    value = "",
                    hint = "Hint text",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedBoxTextField(
                    label = "Label",
                    value = "Value",
                    error = "Error",
                    onValueChange = {},
                )
            }
        }
    }
}
