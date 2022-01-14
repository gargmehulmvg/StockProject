package com.example.stocksapp.interfaces

import com.example.stocksapp.models.responses.StockResponse

interface IHomeFragmentInterface {

    fun onSuccessResponse(commonResponse: StockResponse)

    fun onException(e: Exception)
}