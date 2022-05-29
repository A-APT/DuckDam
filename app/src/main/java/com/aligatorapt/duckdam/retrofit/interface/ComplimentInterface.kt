package com.aligatorapt.duckdam.retrofit.`interface`

import com.aligatorapt.duckdam.dto.compliment.ComplimentRequestDto
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ComplimentInterface {
    @POST("/compliment")
    fun generateCompliment(
        @Body complimentRequestDto: ComplimentRequestDto,
        @HeaderMap httpHeaders: HashMap<String, String>
    ) : Call<ResponseBody>

    @POST("/compliment/slot")
    fun slot(
        @HeaderMap httpHeaders: HashMap<String, String>
    ): Call<ComplimentResponseDto>

    @GET("/compliments")
    fun findCompliments(
        @HeaderMap httpHeaders: HashMap<String, String>
    ): Call<ArrayList<ComplimentResponseDto>>

    @GET("/compliments/{toId}")
    fun findComplimentsByFromAndTo(
        @HeaderMap httpHeaders: HashMap<String, String>,
        @Path("toId") toId: Long
    ): Call<ArrayList<ComplimentResponseDto>>
}
