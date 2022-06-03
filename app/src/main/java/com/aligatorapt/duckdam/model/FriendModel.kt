package com.aligatorapt.duckdam.model

import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.retrofit.RetrofitClient
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.UserCallback
import com.aligatorapt.duckdam.sharedpreferences.MyApplication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

object FriendModel {
    fun followFriend(_targetId: Long, callback: ApiCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.FRIEND_INTERFACE_SERVICE.followFriend(
            httpHeaders = headers,
            targetId = _targetId
        ).enqueue(object: Callback<ResponseBody>{
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    callback.apiCallback(true)
                }else{
                    callback.apiCallback(false)
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.apiCallback(false)
            }

        })
    }

    fun findMyFriend(callback: UserCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.FRIEND_INTERFACE_SERVICE.findMyFriends(
            httpHeaders = headers
        ).enqueue(object: Callback<ArrayList<UserResponseDto>>{
            override fun onResponse(
                call: Call<ArrayList<UserResponseDto>>,
                response: Response<ArrayList<UserResponseDto>>
            ) {
                if(response.isSuccessful){
                    callback.userCallback(true,response.body())
                }else{
                    callback.userCallback(false, null)
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponseDto>>, t: Throwable) {
                callback.userCallback(false, null)
            }

        })
    }
}