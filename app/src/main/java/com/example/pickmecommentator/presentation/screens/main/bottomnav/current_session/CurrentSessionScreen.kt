package com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.pickmecommentator.domain.usecase.FormatTimestampUseCase
import com.example.pickmecommentator.presentation.components.CommentItem
import com.example.pickmecommentator.presentation.components.ServerConnectionElement
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun CurrentSessionScreen(
    viewModel: CurrentSessionScreenViewModel = koinViewModel(),
    formatTimestampUseCase: FormatTimestampUseCase = koinInject()
) {
    val comments by viewModel.comments.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.weight(0.15f)) {
            ServerConnectionElement(viewModel = viewModel)
        }
        Box(
            modifier = Modifier
                .weight(0.85f)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.onPrimary)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(comments) { comment ->
                    CommentItem(
                        comment = comment,
                        formattedTimestamp = formatTimestampUseCase(comment.timestamp)
                    )
                }
            }
        }
    }
}
