package com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.trip_messages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickmecommentator.data.network.models.Message
import com.example.pickmecommentator.domain.usecase.GetTripMessagesUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class TripMessagesScreenState(
    val tripId: String = "",
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true
)

class TripMessagesScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val getTripMessagesUseCase: GetTripMessagesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TripMessagesScreenState())
    val state = _state.asStateFlow()

    init {
        val tripId: String = savedStateHandle.get<String>("tripId") ?: "ID не найден"
        _state.update { it.copy(tripId = tripId) }
        loadMessages()
    }

    fun loadMessages() {
        if (state.value.isLoading || !state.value.hasMore) return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000)
            try {
                val response = getTripMessagesUseCase(
                    tripId = state.value.tripId,
                    offset = state.value.messages.size,
                    limit = 20
                )
                _state.update {
                    it.copy(
                        messages = it.messages + response.messages,
                        isLoading = false,
                        hasMore = response.hasMore
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _state.update { it.copy(messages = emptyList(), hasMore = true) }
            loadMessages()
        }
    }

    fun onErrorDialogDismiss() {
        _state.update { it.copy(error = null) }
    }
}