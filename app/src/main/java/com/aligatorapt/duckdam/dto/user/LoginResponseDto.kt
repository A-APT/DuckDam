package com.aligatorapt.duckdam.dto.user

data class LoginResponseDto (
    val token: String,
    val refreshToken: String
)