package com.digitaldukaan.network

import com.example.stocksapp.BuildConfig
import com.example.stocksapp.network.Apis
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApi {

    private var mAppService: Apis? = null

    fun getServerCallObject(): Apis? {
        if (null == mAppService) {
            val retrofit = Retrofit.Builder().apply {
                baseUrl("https://run.mocky.io/v3/")
                addConverterFactory(GsonConverterFactory.create())
                client(getHttpClient())
            }.build()
            mAppService = retrofit.create(Apis::class.java)
        }
        return mAppService
    }

    private fun getHttpClient(): OkHttpClient {
        val loggingInterface = HttpLoggingInterceptor().apply {
            level = when (BuildConfig.DEBUG) {
                true  -> HttpLoggingInterceptor.Level.BODY
                false -> HttpLoggingInterceptor.Level.NONE
            }
        }
        return OkHttpClient.Builder().apply {
            connectTimeout(1, TimeUnit.MINUTES)
            callTimeout(1, TimeUnit.MINUTES)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
            addInterceptor(loggingInterface)
            protocols(arrayListOf(Protocol.HTTP_1_1, Protocol.HTTP_2))
        }.build()
    }

}
