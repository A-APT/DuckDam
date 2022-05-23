package com.aligatorapt.duckdam.sharedpreferences

import com.aligatorapt.duckdam.dto.ErrorResponseDto
import com.aligatorapt.duckdam.retrofit.RetrofitClient
import com.aligatorapt.duckdam.retrofit.RetrofitClient.retrofit
import retrofit2.Response
import okhttp3.ResponseBody

import retrofit2.Converter
import java.io.IOException


object ErrorUtil {
    fun parseError(response: Response<*>): ErrorResponseDto? {
        val converter: Converter<ResponseBody?, ErrorResponseDto>
            = retrofit.responseBodyConverter(ErrorResponseDto::class.java, arrayOfNulls<Annotation>(0))
        return try {
            converter.convert(response.errorBody())
        } catch (e: IOException) {
            return null
        }
    }
}