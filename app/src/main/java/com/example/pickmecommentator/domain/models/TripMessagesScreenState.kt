package com.example.pickmecommentator.domain.models

import com.example.pickmecommentator.data.network.models.Message

data class TripMessagesScreenState(
    val tripId: String = "",
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val showError: Boolean = false,
    val hasMore: Boolean = true
)