package com.aligatorapt.duckdam.retrofit.`interface`

import com.aligatorapt.duckdam.dto.auth.EmailTokenDto
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface EmailInterface {
    @POST("/user/email")
    fun generateEmailAuth(
        @Body targetEmail: RequestBody
    ) : Call<ResponseBody>

    @POST("/user/email/verify")
    fun verifyEmailToken(
        @Body emailTokenDto: EmailTokenDto
    ) : Call<ResponseBody>
}