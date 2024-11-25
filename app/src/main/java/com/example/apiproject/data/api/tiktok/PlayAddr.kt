package com.example.apiproject.data.api.tiktok

data class PlayAddr(
    val DataSize: String,
    val FileCs: String,
    val FileHash: String,
    val Uri: String,
    val UrlKey: String,
    val UrlList: List<String>
)