package com.example.pickmecommentator.presentation.screens.main.bottomnav.list_of_sessions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickmecommentator.domain.models.TripsScreenState
import com.example.pickmecommentator.domain.usecase.GetTripsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException

class ListOfSessionsScreenViewModel(
    private val getTripsUseCase: GetTripsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(TripsScreenState())
    val state = _state.asStateFlow()

    private var currentPage = 0
    private val pageSize = 10

    init {
        loadTrips()
    }

    fun loadTrips() {
        if (_state.value.isLoading || !_state.value.hasMore) return

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            try {
                val response = getTripsUseCase(
                    offset = currentPage * pageSize,
                    limit = pageSize
                )
                _state.update { currentState ->
                    currentState.copy(
                        trips = currentState.trips + response.trips,
                        isLoading = false,
                        hasMore = response.hasMore
                    )
                }
                currentPage++
            } catch (e: Exception) {
                val errorMessage = when (e) {
                    is IOException -> "Ошибка сети. Проверьте подключение к интернету."
                    else -> "Произошла неизвестная ошибка."
                }
                _state.update { it.copy(isLoading = false, error = errorMessage) }
            }
        }
    }
}
