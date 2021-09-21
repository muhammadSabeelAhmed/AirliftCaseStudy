package com.sabeeldev.myapplication.data.repositories

import com.sabeeldev.myapplication.Util.getApiKey
import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse
import com.sabeeldev.myapplication.data.network.MyApi
import com.sabeeldev.myapplication.data.network.SafeApiRequest
import com.sabeeldev.myapplication.data.network.responses.CoinsListResponse
import com.sabeeldev.myapplication.data.network.responses.ConversionResponse

class UserRepository(
    private val api: MyApi
) : SafeApiRequest() {

    suspend fun getCryptoData(target: String): CryptoRatesResponse {
        return apiRequest { api.getCryptoData(getApiKey(), "1", target) }
    }

    suspend fun getCoinsList(): CoinsListResponse {
        return apiRequest { api.getCoinsList(getApiKey()) }
    }

    suspend fun getConversion(from: String?, to: String?, amount: Int?): ConversionResponse {
        return apiRequest { api.getConversion(getApiKey(), from, to, amount) }
    }

}