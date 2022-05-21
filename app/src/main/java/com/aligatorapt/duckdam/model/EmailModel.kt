package com.aligatorapt.duckdam.model

import android.util.Log
import com.aligatorapt.duckdam.dto.auth.EmailTokenDto
import com.aligatorapt.duckdam.retrofit.RetrofitClient
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object EmailModel {
    fun generateEmailAuth(_email: String, callback: ApiCallback){
        RetrofitClient.EMAIL_INTERFACE_SERVICE.generateEmailAuth(_email).enqueue(object: Callback<ResponseBody> {
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

    fun verifyEmailToken(_emailTokenDto: EmailTokenDto, callback: ApiCallback): Boolean{
        var isVerified = false
        RetrofitClient.EMAIL_INTERFACE_SERVICE.verifyEmailToken(_emailTokenDto).enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                Log.d("Response::", "Response::" + response.body().toString())
                isVerified = true
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("onFailure::", "onFailure: $t")
                isVerified = false
            }
        })
        return isVerified
    }
}