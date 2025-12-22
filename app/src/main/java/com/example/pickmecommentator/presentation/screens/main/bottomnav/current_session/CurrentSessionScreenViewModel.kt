package com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickmecommentator.domain.models.Comment
import com.example.pickmecommentator.domain.models.ConnectionState
import com.example.pickmecommentator.domain.repositories.SocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrentSessionScreenViewModel(
    private val socketRepository: SocketRepository
): ViewModel() {
    val connectionState: StateFlow<ConnectionState> = socketRepository.connectionState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = ConnectionState()
        )

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    init {
        observeComments()
    }

    fun onSwitchChanged() {
        viewModelScope.launch {
            if (!connectionState.value.isConnected) {
                socketRepository.connect()
            } else {
                socketRepository.disconnect()
            }
        }
    }

    private fun observeComments () {
        viewModelScope.launch {
            socketRepository.incomingComments.collect { newComment ->
                _comments.update { currentList ->
                    currentList + newComment
                }
            }
        }
    }
}
