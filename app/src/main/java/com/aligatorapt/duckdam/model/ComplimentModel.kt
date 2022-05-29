package com.aligatorapt.duckdam.model

import com.aligatorapt.duckdam.dto.compliment.ComplimentRequestDto
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.retrofit.RetrofitClient
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.ComplimentCallback
import com.aligatorapt.duckdam.retrofit.callback.ComplimentsCallback
import com.aligatorapt.duckdam.sharedpreferences.MyApplication
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ComplimentModel {
    fun generateCompliment(_complimentRequestDto: ComplimentRequestDto, callback: ApiCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.COMPLIMENT_INTERFACE_SERVICE.generateCompliment(
            complimentRequestDto = _complimentRequestDto,
            httpHeaders = headers
        ).enqueue(object: Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    callback.apiCallback(true)
                } else {
                    callback.apiCallback(false)
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                callback.apiCallback(false)
            }
        })
    }

    fun slot(callback: ComplimentCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.COMPLIMENT_INTERFACE_SERVICE.slot(
            httpHeaders = headers
        ).enqueue(object : Callback<ComplimentResponseDto> {
            override fun onResponse(
                call: Call<ComplimentResponseDto>,
                response: Response<ComplimentResponseDto>
            ) {
                if (response.isSuccessful) {
                    callback.complimentCallback(true, response.body())
                } else {
                    callback.complimentCallback(false, null)
                }
            }

            override fun onFailure(call: Call<ComplimentResponseDto>, t: Throwable) {
                callback.complimentCallback(false, null)
            }
        })
    }

    fun findCompliments(callback: ComplimentsCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.COMPLIMENT_INTERFACE_SERVICE.findCompliments(
            httpHeaders = headers
        ).enqueue(object : Callback<ArrayList<ComplimentResponseDto>> {
            override fun onResponse(
                call: Call<ArrayList<ComplimentResponseDto>>,
                response: Response<ArrayList<ComplimentResponseDto>>
            ) {
                if (response.isSuccessful) {
                    callback.complimentsCallback(true, response.body())
                } else {
                    callback.complimentsCallback(false, null)
                }
            }

            override fun onFailure(call: Call<ArrayList<ComplimentResponseDto>>, t: Throwable) {
                callback.complimentsCallback(false, null)
            }
        })
    }

    fun findComplimentsByFromAndTo(_toId:Long, callback: ComplimentsCallback){
        val headers = HashMap<String, String>()
        val userToken = MyApplication.prefs.getString("token", "notExist")
        headers["Authorization"] = "Bearer $userToken"

        RetrofitClient.COMPLIMENT_INTERFACE_SERVICE.findComplimentsByFromAndTo(
            httpHeaders = headers,
            toId = _toId
        ).enqueue(object : Callback<ArrayList<ComplimentResponseDto>> {
            override fun onResponse(
                call: Call<ArrayList<ComplimentResponseDto>>,
                response: Response<ArrayList<ComplimentResponseDto>>
            ) {
                if (response.isSuccessful) {
                    callback.complimentsCallback(true, response.body())
                } else {
                    callback.complimentsCallback(false, null)
                }
            }

            override fun onFailure(call: Call<ArrayList<ComplimentResponseDto>>, t: Throwable) {
                callback.complimentsCallback(false, null)
            }
        })
    }
}
