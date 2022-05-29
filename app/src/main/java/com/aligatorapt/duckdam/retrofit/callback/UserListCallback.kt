package com.aligatorapt.duckdam.retrofit.callback

import com.aligatorapt.duckdam.dto.user.UserResponseDto

interface UserListCallback {
    fun userListCallback(flag: Boolean, data: ArrayList<UserResponseDto>?)
}