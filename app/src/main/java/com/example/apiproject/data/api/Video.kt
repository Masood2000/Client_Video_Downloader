package com.example.apiproject.data.api

data class Video(
    val height: Int,
    val id: String,
    val type: Int,
    val url: String,
    val width: Int,

    var imageUrl: String? = null
)