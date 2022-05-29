package com.aligatorapt.duckdam.retrofit.`interface`

import com.aligatorapt.duckdam.dto.user.LoginRequestDto
import com.aligatorapt.duckdam.dto.user.LoginResponseDto
import com.aligatorapt.duckdam.dto.user.RegisterDto
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

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

    @GET("/users/{query}")
    fun searchByName(
        @HeaderMap httpHeaders: HashMap<String, String>,
        @Path("query") query: String
    ): Call<ArrayList<UserResponseDto>>

    @GET("/user/stickers")
    fun getStickerList(
        @HeaderMap httpHeaders: HashMap<String, String>
    ): Call<ArrayList<Boolean>>

    @GET("/user/slot")
    fun isEligibleForSlot(
        @HeaderMap httpHeaders: HashMap<String, String>,
    ): Call<Boolean>

}