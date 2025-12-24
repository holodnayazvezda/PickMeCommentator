package com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.trip_messages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class TripMessagesScreenState(
    val tripId: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class TripMessagesViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(TripMessagesScreenState())
    val state = _state.asStateFlow()

    init {
        val tripId: String = savedStateHandle.get<String>("tripId") ?: "ID не найден"
        _state.update { it.copy(tripId = tripId) }
    }
}