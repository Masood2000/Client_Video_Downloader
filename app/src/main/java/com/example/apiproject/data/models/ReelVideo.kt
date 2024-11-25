package com.example.apiproject.data.models
import kotlinx.serialization.Serializable
@Serializable
data class ReelVideo(
    val id: String,
        val title: String,
        val channel: String,
        val owner: String,
        val url: String,
        val thumbnail: String?

)
