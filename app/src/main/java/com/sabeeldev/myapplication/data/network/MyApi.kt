package com.sabeeldev.myapplication.data.network

import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse
import com.sabeeldev.myapplication.data.network.responses.CoinsListResponse
import com.sabeeldev.myapplication.data.network.responses.ConversionResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("live")
    suspend fun getCryptoData(
        @Query("access_key") access_key: String,
        @Query("expand") expand: String,
        @Query("target") target: String
    ): Response<CryptoRatesResponse>

    @GET("list")
    suspend fun getCoinsList(
        @Query("access_key") access_key: String
    ): Response<CoinsListResponse>

    @GET("convert")
    suspend fun getConversion(
        @Query("access_key") access_key: String?,
        @Query("from") from: String?,
        @Query("to") to: String?,
        @Query("amount") amount: Int?
    ): Response<ConversionResponse>


    companion object {
        operator fun invoke(): MyApi {
            return Retrofit.Builder()
                .baseUrl("http://api.coinlayer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}
