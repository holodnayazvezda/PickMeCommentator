package com.example.pickmecommentator.data.network.models

import com.google.gson.annotations.SerializedName

data class Message(
    val id: Int,
    @SerializedName("message_id")
    val messageId: Int,
    val data: MessageData
)
