package com.sabeeldev.myapplication.ui.ViewModel

import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse
import com.sabeeldev.myapplication.data.network.responses.CoinsListResponse
import com.sabeeldev.myapplication.data.network.responses.ConversionResponse

interface CryptoListner {
    fun onStarted()
    fun onConversion(response: ConversionResponse?)
    fun onSuccess(response: CryptoRatesResponse?)
    fun onSuccess(response: CoinsListResponse?)
    fun onFailure(message: String)
}