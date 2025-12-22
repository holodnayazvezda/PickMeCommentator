package com.example.pickmecommentator.domain.models

data class ConnectionState(
    val isConnected: Boolean = false,
    val errorMessage: String? = null
)
