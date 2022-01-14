package com.example.stocksapp.services.networkcall

import android.util.Log
import com.digitaldukaan.network.RetrofitApi
import com.example.stocksapp.interfaces.IHomeFragmentInterface

class HomeFragmentNetworkService {

    suspend fun getStocksDataServerCall(splashServiceInterface: IHomeFragmentInterface) {
        try {
            val response = RetrofitApi().getServerCallObject()?.getStocksData()
            response?.let {
                if (it.isSuccessful) {
                    it.body()?.let { staticTextResponse ->
                        splashServiceInterface.onSuccessResponse(staticTextResponse)
                    }
                } else splashServiceInterface.onException(Exception(response.message()))
            }
        } catch (e: Exception) {
            Log.e(HomeFragmentNetworkService::class.java.simpleName, "getStocksDataServerCall: ", e)
            splashServiceInterface.onException(e)
        }
    }

}