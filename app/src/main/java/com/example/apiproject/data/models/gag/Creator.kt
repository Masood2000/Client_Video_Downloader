package com.example.apiproject.data.models.gag

data class Creator(
    val about: String,
    val accountId: String,
    val activeTs: Int,
    val avatarUrl: String,
    val creationTs: Int,
    val emojiStatus: String,
    val fullName: String,
    val isActivePro: Boolean,
    val isActiveProPlus: Boolean,
    val isVerifiedAccount: Boolean,
    val preferences: Preferences,
    val profileUrl: String,
    val userId: String,
    val username: String
)