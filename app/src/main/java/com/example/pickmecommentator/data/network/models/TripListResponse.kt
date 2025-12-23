package com.example.pickmecommentator.data.network.models

import com.google.gson.annotations.SerializedName

data class TripListResponse(
    val trips: List<Trip>,
    val total: Int,
    val offset: Int,
    val limit: Int,
    @SerializedName("has_more")
    val hasMore: Boolean
)
