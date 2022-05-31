package com.aligatorapt.duckdam.dto.user

data class LoginResponseDto (
    val token: String,
    val refreshToken: String,
    val uid: Long,
    val name: String,
    val profile: ByteArray?
)