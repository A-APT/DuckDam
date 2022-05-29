package com.aligatorapt.duckdam.model

import android.util.Log
import com.aligatorapt.duckdam.dto.user.LoginRequestDto
import com.aligatorapt.duckdam.dto.user.LoginResponseDto
import com.aligatorapt.duckdam.dto.user.RegisterDto
import com.aligatorapt.duckdam.retrofit.RetrofitClient
import com.aligatorapt.duckdam.sharedpreferences.MyApplication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.aligatorapt.duckdam.dto.ErrorResponseDto
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.retrofit.callback.*
import com.aligatorapt.duckdam.sharedpreferences.ErrorUtil

object UserModel{
    fun register( _userInfo: RegisterDto, callback: RegisterCallback){
        RetrofitClient.USER_INTERFACE_SERVICE.register(_userInfo).enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                Log.d("Response::", "Response::" + response.body().toString())
                if (response.isSuccessful) {
                    callback.registerCallback(true, isNickname = false)
                } else {
                    val error: ErrorResponseDto? = ErrorUtil.parseError(response)
                    Log.d("errorResponse::", "errorResponse::" + error!!.statusCode + error.message)
                    if(error.statusCode == 409){
                        callback.registerCallback(false, isNickname = true)
                    }
                    else callback.registerCallback(false, isNickname = false)
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("onFailure::", "onFailure: $t")
                callback.registerCallback(false, isNickname = false)
            }
        })
    }

    fun login(_loginRequestDto: LoginRequestDto, callback: ApiCallback){
        RetrofitClient.USER_INTERFACE_SERVICE.login(_loginRequestDto).enqueue(object :Callback<LoginResponseDto>{
            override fun onResponse(
                call: Call<LoginResponseDto>,
                response: Response<LoginResponseDto>
            ) {
                Log.d("Response::", "Response::" + response.body().toString())
                if(response.isSuccessful){
                    MyApplication.prefs.setString("token", response.body()!!.token)
                    MyApplication.prefs.setString("refreshToken", response.body()!!.refreshToken)
                    callback.apiCallback(true)
                }else{
                    callback.apiCallback(false)
                }
            }
            override fun onFailure(call: Call<LoginResponseDto>, t: Throwable) {
                Log.d("onFailure::", "onFailure: $t")
                callback.apiCallback(false)
            }
        })
    }

    fun isEligibleForSlot(callback: BooleanCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.USER_INTERFACE_SERVICE.isEligibleForSlot(
                httpHeaders = headers
            ).enqueue(object :Callback<Boolean>{
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if(response.isSuccessful) {
                    callback.booleanCallback(true, response.body())
                }else{
                    callback.booleanCallback(false, null)
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                callback.booleanCallback(false, null)
            }
        })
    }

    fun searchByName(_query: String, callback: UserListCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.USER_INTERFACE_SERVICE.searchByName(
            httpHeaders = headers,
            query = _query).enqueue(object :Callback<ArrayList<UserResponseDto>>{
            override fun onResponse(call: Call<ArrayList<UserResponseDto>>, response: Response<ArrayList<UserResponseDto>>) {

                Log.d("Response::", "Response::" + response.body().toString())
                if(response.isSuccessful){
                    callback.userListCallback(true, response.body())
                }else{
                    callback.userListCallback(false,null)
                }
            }

            override fun onFailure(call: Call<ArrayList<UserResponseDto>>, t: Throwable) {
                callback.userListCallback(false, null)
            }
        })
    }

    fun getStickerList(callback: BooleanListCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.USER_INTERFACE_SERVICE.getStickerList(httpHeaders = headers).enqueue(object: Callback<ArrayList<Boolean>>{
            override fun onResponse(call: Call<ArrayList<Boolean>>, response: Response<ArrayList<Boolean>>) {

                Log.d("Response::", "Response::" + response.body().toString())
                if(response.isSuccessful){
                    callback.booleanlistCallback(true, response.body())
                }else{
                    callback.booleanlistCallback(false, null)
                }
            }

            override fun onFailure(call: Call<ArrayList<Boolean>>, t: Throwable) {
                callback.booleanlistCallback(false, null)
            }
        })
    }
}
