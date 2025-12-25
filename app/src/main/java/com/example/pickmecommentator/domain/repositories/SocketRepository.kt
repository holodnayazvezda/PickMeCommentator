package com.example.pickmecommentator.domain.repositories

import com.example.pickmecommentator.domain.models.CommentModel
import com.example.pickmecommentator.domain.models.ConnectionState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface SocketRepository {
    val connectionState: StateFlow<ConnectionState>

    val incomingComments: Flow<CommentModel>

    suspend fun connect()
    suspend fun disconnect()
}
