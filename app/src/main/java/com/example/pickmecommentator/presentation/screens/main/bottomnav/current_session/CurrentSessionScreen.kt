package com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pickmecommentator.presentation.components.ServerConnectionElement
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrentSessionScreen(
    viewModel: CurrentSessionScreenViewModel = koinViewModel()
) {
    val comments by viewModel.comments.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(0.15f)) {
            ServerConnectionElement(viewModel = viewModel)
        }
        Box(modifier = Modifier.weight(0.85f)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(comments) {
                    Text(text = it.comment)
                }
            }
        }
    }
}