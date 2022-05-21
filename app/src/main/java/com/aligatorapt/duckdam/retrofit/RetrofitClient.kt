package com.aligatorapt.duckdam.retrofit

import com.aligatorapt.duckdam.retrofit.`interface`.EmailInterface
import com.aligatorapt.duckdam.retrofit.`interface`.UserInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL:String = "http://USER_IP:8080"

    private val loggingInterceptor = HttpLoggingInterceptor()

    val client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30000, TimeUnit.MILLISECONDS)
        .connectTimeout(30000, TimeUnit.MILLISECONDS)
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit.Builder by lazy{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val EMAIL_INTERFACE_SERVICE: EmailInterface by lazy{
        retrofit.build().create(EmailInterface::class.java)
    }

    val USER_INTERFACE_SERVICE: UserInterface by lazy{
        retrofit.build().create(UserInterface::class.java)
    }

}
