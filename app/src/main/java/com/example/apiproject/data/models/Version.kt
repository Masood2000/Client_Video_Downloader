package com.example.apiproject.data.models

data class Version(
    val current_git_head: Any,
    val release_git_head: String,
    val repository: String,
    val version: String
)