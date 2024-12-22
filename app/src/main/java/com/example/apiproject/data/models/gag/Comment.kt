package com.example.apiproject.data.models.gag

data class Comment(
    val canAnonymous: Boolean,
    val latestCommentText: String,
    val listType: String,
    val opToken: String,
    val pinnedCommentCount: Int,
    val updateTs: Int
)