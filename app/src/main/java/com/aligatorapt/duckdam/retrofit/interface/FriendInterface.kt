package com.aligatorapt.duckdam.retrofit.`interface`

import com.aligatorapt.duckdam.dto.user.UserResponseDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

interface FriendInterface {
    @POST("/friend/follow/{targetId}")
    fun followFriend(
        @HeaderMap httpHeaders: Map<String, String>,
        @Path("targetId") targetId: Long)
    : Call<ResponseBody>

    @GET("/friend")
    fun findMyFriends(
        @HeaderMap httpHeaders: Map<String, String>)
    : Call<ArrayList<UserResponseDto>>
}