package com.example.android.bintest.network

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://lookup.binlist.net/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface BinApiService{
    @GET("{number}")
    suspend fun getInfo(@Path("number") number: String) : CardInfo
}

object BinAPI {
    val retrofitService: BinApiService by lazy {
        retrofit.create(BinApiService::class.java)
    }
}
