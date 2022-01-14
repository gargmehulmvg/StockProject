package com.example.stocksapp.network

import com.example.stocksapp.models.responses.StockResponse
import retrofit2.Response
import retrofit2.http.*

interface Apis {

    @GET("6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getStocksData() : Response<StockResponse>
}