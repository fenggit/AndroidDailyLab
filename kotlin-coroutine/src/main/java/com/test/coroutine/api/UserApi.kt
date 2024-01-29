package com.test.coroutine.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * author : felix
 * date : 2023/10/10
 * description :
 */


data class UserLoginReq(val username: String, val password: Int)

data class UserLoginRes(val data: String)

interface UserServiceApi {
    @POST("/user/login")
    suspend fun login(@Body body: UserLoginReq): Call<UserLoginRes>
}

val userServiceApi: UserServiceApi by lazy {
    val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor {
            it.proceed(it.request()).apply {
                Log.d("http", "body = ${body()?.string()}")
            }
        }.build())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://www.wanandroid.com")
        .build()
    retrofit.create(UserServiceApi::class.java)
}