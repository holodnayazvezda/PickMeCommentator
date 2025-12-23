package com.example.pickmecommentator.domain.models

import com.example.pickmecommentator.data.network.models.Trip

data class TripsScreenState(
    val trips: List<Trip> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true
)