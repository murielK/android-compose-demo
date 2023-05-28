package com.example.pta.ui.core.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by muriel on 27.05.2023..
 */

@Composable
fun ListDivider() {
    Divider(
        modifier = Modifier.padding(8.dp),
        color = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
    )
}