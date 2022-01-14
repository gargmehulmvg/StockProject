package com.example.stocksapp.services

import com.example.stocksapp.interfaces.IHomeFragmentInterface
import com.example.stocksapp.services.networkcall.HomeFragmentNetworkService
import com.example.stocksapp.views.constants.CoroutineScopeUtils

class HomeFragmentService {

    private val mService = HomeFragmentNetworkService()
    private lateinit var mInterface: IHomeFragmentInterface

    fun setHomeFragmentInterface(serviceInterface: IHomeFragmentInterface) {
        mInterface = serviceInterface
    }

    fun getStocksData() {
        CoroutineScopeUtils().runTaskOnCoroutineBackground {
            mService.getStocksDataServerCall(mInterface)
        }
    }

}