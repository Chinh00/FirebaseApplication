package com.supperman.firebaseapplication.application.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoadingButton (
    isLoading: State<Boolean>,
    content: @Composable () -> Unit
) {
    Button(
        onClick = {

        }
    ) {
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier.apply {
                    width(10.dp)
                },
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.secondary
            )
        } else {
            content()
        }
    }
}