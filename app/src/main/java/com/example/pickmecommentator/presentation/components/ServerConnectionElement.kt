package com.example.pickmecommentator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentSessionScreenViewModel
import com.example.pickmecommentator.presentation.theme.StatusGreen
import com.example.pickmecommentator.presentation.theme.SwitchBlue

@Composable
fun ServerConnectionElement(
    viewModel: CurrentSessionScreenViewModel
) {
    val connectionState by viewModel.connectionState.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val statusText = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Normal, color = MaterialTheme.colorScheme.onSurfaceVariant)) {
                append("Статус: ")
            }
            if (connectionState.isConnected) {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = StatusGreen)) {
                    append("Подключено")
                }
            } else {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f))) {
                    append("Отключено")
                }
            }
        }
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = statusText,
            style = MaterialTheme.typography.bodyLarge
        )

        Switch(
            modifier = Modifier.padding(end = 16.dp),
            checked = connectionState.isConnected,
            onCheckedChange = {
                viewModel.onSwitchChanged()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = SwitchBlue,
                checkedTrackColor = SwitchBlue.copy(alpha = 0.5f),
                uncheckedThumbColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                uncheckedTrackColor = MaterialTheme.colorScheme.surface
            ),
            thumbContent = if (connectionState.isConnected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                        tint = MaterialTheme.colorScheme.onPrimary // White icon
                    )
                }
            } else {
                null
            }
        )
    }
}
