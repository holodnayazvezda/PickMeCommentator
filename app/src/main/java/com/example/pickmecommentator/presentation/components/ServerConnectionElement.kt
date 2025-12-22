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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session.CurrentSessionScreenViewModel

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
            .background(Color(0xFF20264D)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val statusText = buildAnnotatedString {
            withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                append("Статус: ")
            }
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append(
                    if (connectionState.isConnected) {
                        "Подключено"
                    } else {
                        "Отключено"
                    }
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = statusText,
            style = TextStyle(
                fontSize = 18.sp
            )
        )

        Switch(
            modifier = Modifier.padding(end = 5.dp),
            checked = connectionState.isConnected,
            onCheckedChange = {
                viewModel.onSwitchChanged()
            },
            thumbContent = if (connectionState.isConnected) {
                {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize),
                    )
                }
            } else {
                null
            }
        )
    }
}