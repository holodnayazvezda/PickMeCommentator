package com.example.pickmecommentator.data.network.repository

import android.util.Log
import com.example.pickmecommentator.common.Constants
import com.example.pickmecommentator.domain.models.Comment
import com.example.pickmecommentator.domain.models.ConnectionState
import com.example.pickmecommentator.domain.repositories.SocketRepository
import com.google.gson.Gson
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.engineio.client.EngineIOException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.update
import org.json.JSONObject
import java.net.ConnectException
import java.net.URISyntaxException

class SocketRepositoryImpl : SocketRepository {
    private var socket: Socket? = null
    private val gson = Gson()

    private val _connectionState = MutableStateFlow(ConnectionState())
    override val connectionState: StateFlow<ConnectionState> = _connectionState.asStateFlow()

    init {
        try {
            val options = IO.Options().apply {
                reconnection = false
                forceNew = true
            }

            socket = IO.socket(Constants.socketServerUrl, options)
            setupListeners()
        } catch(e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupListeners() {
        socket?.on(Socket.EVENT_CONNECT) {
            _connectionState.update { it.copy(isConnected = true, errorMessage = "") }
            Log.d("SocketRepo", "Connected")
        }
        socket?.on(Socket.EVENT_DISCONNECT) {
            val error = it.getOrNull(0)
            val errorMessage = if (error == "ping timeout") {
                "Ошибка соединения, скорее всего нет интернета, или что-то не так с сервером."
            } else {
                ""
            }
            _connectionState.update { it.copy(isConnected = false, errorMessage = errorMessage) }
            Log.d("SocketRepo", "Disconnected")
        }
        socket?.on(Socket.EVENT_CONNECT_ERROR) {
            val error = it.getOrNull(0)
            val errorMessage = if (error is EngineIOException) {
                "Ошибка соединения, скорее всего нет интернета, или что-то не так с сервером."
            } else {
                "Неизвестная ошибка соединения."
            }
            _connectionState.update { it.copy(isConnected = false, errorMessage = errorMessage) }
            Log.e("SocketRepo", "Connection Error")
        }
    }

    override val incomingComments: Flow<Comment> = callbackFlow {
        val listener = io.socket.emitter.Emitter.Listener { args ->
            if (args.isNotEmpty()) {
                val data = args[0] as JSONObject
                try {
                    val comment = gson.fromJson(data.toString(), Comment::class.java)
                    trySend(comment)
                } catch (e: Exception) {
                    Log.e("SocketRepo", "Parse error", e)
                }
            }

        }

        socket?.on("new_comment", listener)

        awaitClose {
            socket?.off("new_comment", listener)
        }
    }

    override suspend fun connect() {
        if (socket?.connected() == false) {
            _connectionState.update { it.copy(errorMessage = "") }
            socket?.connect()
        }
    }

    override suspend fun disconnect() {
        socket?.disconnect()
    }
}
