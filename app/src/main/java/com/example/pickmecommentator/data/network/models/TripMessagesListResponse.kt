package com.example.pickmecommentator.data.network.models

import com.google.gson.annotations.SerializedName

data class TripMessagesListResponse(
    val messages: List<Message>,
    val total: Int,
    val offset: Int,
    val limit: Int,
    @SerializedName("has_more")
    val hasMore: Boolean
)
