package com.example.pickmecommentator.domain.models

import kotlinx.serialization.SerialName

data class CommentModel(
    @SerialName("batch_id")
    val batchId: Int,
    @SerialName("need_to_comment")
    val needToComment: Boolean,
    val comment: String,
    val timestamp: Long
)