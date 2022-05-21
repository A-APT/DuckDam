package com.aligatorapt.duckdam.dto.auth

data class EmailTokenDto (
    var email: String,
    var token: String
)