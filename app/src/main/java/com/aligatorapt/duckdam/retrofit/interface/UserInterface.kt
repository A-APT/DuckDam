package com.aligatorapt.duckdam.retrofit.`interface`

import com.aligatorapt.duckdam.dto.user.LoginRequestDto
import com.aligatorapt.duckdam.dto.user.LoginResponseDto
import com.aligatorapt.duckdam.dto.user.RegisterDto
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface UserInterface {
    @POST("/user/register")
    fun register(
        @Body registerDto: RegisterDto
    ): Call<ResponseBody>

    @POST("/user/login")
    fun login(
        @Body loginRequestDto: LoginRequestDto
    ): Call<LoginResponseDto>

    @POST("/user/refresh")
    fun refreshToken(
        @Body refreshToken: String
    ): Call<LoginResponseDto>

    @POST("/users/{query}")
    fun searchByName(
        @HeaderMap httpHeaders: HashMap<String, String>,
        @Path("query") query: String
    ): Call<List<UserResponseDto>>

    @POST("/user/stickers")
    fun getStickerList(
        @HeaderMap httpHeaders: HashMap<String, String>
    ): Call<List<Boolean>>

    @POST("/user/slot")
    fun isEligibleForSlot(
        @HeaderMap httpHeaders: HashMap<String, String>,
    ): Call<Boolean>

}