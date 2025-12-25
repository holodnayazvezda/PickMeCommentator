package com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.trip_messages

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickmecommentator.domain.models.TripMessagesScreenState
import com.example.pickmecommentator.domain.usecase.GetTripMessagesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException


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
            try {
                val response = getTripMessagesUseCase(
                    tripId = state.value.tripId,
                    offset = state.value.messages.size,
                    limit = 10
                )
                _state.update {
                    it.copy(
                        messages = it.messages + response.messages,
                        isLoading = false,
                        error = null,
                        hasMore = response.hasMore
                    )
                }
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is IOException -> "Проверьте подключение к интернету и состояние сервера."
                    else -> "Произошла неизвестная ошибка."
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                        error = errorMessage,
                        showError = true
                    )
                }
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
        _state.update { it.copy(showError = false) }
    }
}