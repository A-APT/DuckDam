package com.aligatorapt.duckdam.retrofit

import com.aligatorapt.duckdam.dto.ErrorResponseDto
import com.aligatorapt.duckdam.retrofit.`interface`.EmailInterface
import com.aligatorapt.duckdam.retrofit.`interface`.UserInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.aligatorapt.duckdam.retrofit.`interface`.ComplimentInterface
import com.aligatorapt.duckdam.retrofit.`interface`.FriendInterface

object RetrofitClient {
    private const val BASE_URL:String = "http://172.30.1.28:8080"

    private val loggingInterceptor = HttpLoggingInterceptor()

    private val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30000, TimeUnit.MILLISECONDS)
        .connectTimeout(30000, TimeUnit.MILLISECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    val builder: Retrofit.Builder by lazy{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val retrofit: Retrofit = builder.build()

    val EMAIL_INTERFACE_SERVICE: EmailInterface by lazy{
        retrofit.create(EmailInterface::class.java)
    }

    val USER_INTERFACE_SERVICE: UserInterface by lazy{
        retrofit.create(UserInterface::class.java)
    }

    val COMPLIMENT_INTERFACE_SERVICE: ComplimentInterface by lazy{
        retrofit.create(ComplimentInterface::class.java)
    }

    val FRIEND_INTERFACE_SERVICE: FriendInterface by lazy{
        retrofit.create(FriendInterface::class.java)
    }
}
