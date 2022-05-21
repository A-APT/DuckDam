package com.aligatorapt.duckdam.model

import android.util.Log
import com.aligatorapt.duckdam.dto.user.LoginRequestDto
import com.aligatorapt.duckdam.dto.user.LoginResponseDto
import com.aligatorapt.duckdam.dto.user.RegisterDto
import com.aligatorapt.duckdam.retrofit.RetrofitClient
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.sharedpreferences.MyApplication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserModel{
    fun register( _userInfo: RegisterDto, callback: ApiCallback){
        RetrofitClient.USER_INTERFACE_SERVICE.register(_userInfo).enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                Log.d("Response::", "Response::" + response.body().toString())
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("onFailure::", "onFailure: $t")
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
            }
        })
    }
}