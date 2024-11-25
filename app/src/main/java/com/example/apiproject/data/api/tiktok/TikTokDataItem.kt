package com.example.apiproject.data.api.tiktok

import com.example.apiproject.data.api.tiktok.PlayAddr

data class TikTokDataItem(
    val Bitrate: Int,
    val CodecType: String,
    val GearName: String,
    val PlayAddr: PlayAddr,
    val QualityType: Int
)