package com.example.pickmecommentator.presentation.screens.main.bottomnav.current_session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pickmecommentator.domain.models.Comment
import com.example.pickmecommentator.domain.models.ConnectionState
import com.example.pickmecommentator.domain.repositories.SocketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrentSessionScreenViewModel(
    private val socketRepository: SocketRepository
): ViewModel() {

    private val _connectionState = MutableStateFlow(ConnectionState())
    val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    private val _comments = MutableStateFlow<List<Comment>>(emptyList())
    val comments: StateFlow<List<Comment>> = _comments.asStateFlow()

    init {
        // Observe the repository state and update the ViewModel's state
        socketRepository.connectionState
            .onEach { repoState -> _connectionState.value = repoState }
            .launchIn(viewModelScope)

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

    fun onErrorDialogDismiss() {
        // Update the ViewModel's state directly, not the repository's
        _connectionState.update { it.copy(errorMessage = "") }
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
