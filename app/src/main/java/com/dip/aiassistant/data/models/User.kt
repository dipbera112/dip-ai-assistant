package com.dip.aiassistant.data.models

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val profileImageUrl: String = "",
    val language: String = "en",
    val theme: String = "dark",
    val createdAt: Long = System.currentTimeMillis()
)
