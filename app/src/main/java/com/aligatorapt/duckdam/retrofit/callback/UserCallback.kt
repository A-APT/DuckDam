package com.aligatorapt.duckdam.retrofit.callback

import com.aligatorapt.duckdam.dto.user.UserResponseDto

interface UserCallback {
    fun userCallback(flag: Boolean, data: ArrayList<UserResponseDto>?)
}