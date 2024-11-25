package com.example.apiproject.data.models

data class Format(
    val abr: Any,
    val acodec: String,
    val aspect_ratio: Double,
    val audio_ext: String,
    val cookies: String,
    val dynamic_range: String,
    val ext: String,
    val filesize: Int,
    val format: String,
    val format_id: String,
    val format_note: String,
    val height: Int,
    val http_headers: HttpHeadersX,
    val preference: Int,
    val protocol: String,
    val quality: Int,
    val resolution: String,
    val tbr: Int,
    val url: String,
    val vbr: Any,
    val vcodec: String,
    val video_ext: String,
    val width: Int
)