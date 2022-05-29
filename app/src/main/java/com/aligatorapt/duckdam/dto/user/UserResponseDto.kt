package com.aligatorapt.duckdam.dto.user

import java.io.Serializable

data class UserResponseDto (
    val uid: Long,
    val name: String,
    val profile: String?
): Serializable