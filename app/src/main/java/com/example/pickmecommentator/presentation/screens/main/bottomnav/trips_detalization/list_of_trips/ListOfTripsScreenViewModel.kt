package com.example.pickmecommentator.presentation.screens.main.bottomnav.trips_detalization.list_of_trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickmecommentator.domain.models.TripsScreenState
import com.example.pickmecommentator.domain.usecase.GetTripsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class ListOfTripsScreenViewModel(
    private val getTripsUseCase: GetTripsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TripsScreenState())
    val state = _state.asStateFlow()

    init {
        loadTrips()
    }

    fun loadTrips() {
        if (_state.value.isLoading || !_state.value.hasMore) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                val response = getTripsUseCase(
                    offset = _state.value.trips.size,
                    limit = 10
                )
                _state.update { currentState ->
                    currentState.copy(
                        trips = currentState.trips + response.trips,
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
            _state.update { it.copy(trips = emptyList(), hasMore = true) }
            loadTrips()
        }
    }

    fun onErrorDialogDismiss() {
        _state.update { it.copy(showError = false) }
    }
}
