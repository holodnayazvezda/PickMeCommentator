package com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.trip_messages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripMessagesScreen(
    modifier: Modifier = Modifier,
    viewModel: TripMessagesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "ID поездки: ${state.tripId}")
    }
}