package com.example.pickmecommentator.presentation.screens.main.bottomnav.list_of_sessions

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListOfSessionsScreen(
    viewModel: ListOfSessionsScreenViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.trips.isEmpty() && state.isLoading) {
            // Show a full-screen loading indicator for the initial load
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(state.trips) { index, trip ->
                    // Simple display of trip info
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Trip ID: ${trip.id}",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Date: ${trip.date}",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 13.sp
                        )
                    }

                    // Check if the user has scrolled to the end of the list
                    if (index == state.trips.size - 1 && !state.isLoading && state.hasMore) {
                        LaunchedEffect(Unit) {
                            viewModel.loadTrips()
                        }
                    }
                }

                // Show a loading indicator at the bottom when loading more items
                if (state.isLoading && state.trips.isNotEmpty()) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }

        // Show error message if any
        state.error?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
