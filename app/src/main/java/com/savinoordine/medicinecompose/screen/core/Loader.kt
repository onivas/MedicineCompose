package com.savinoordine.medicinecompose.screen.core

import androidx.compose.foundation.layout.height
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.savinoordine.medicinecompose.ui.theme.Green700

@Composable
fun Loader(isLoading: Boolean) {
    if (isLoading) {
        LinearProgressIndicator(
            modifier = Modifier.height(3.dp),
            backgroundColor = Green700,
        )
    }
}