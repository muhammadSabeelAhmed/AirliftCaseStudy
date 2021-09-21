package com.sabeeldev.myapplication.data.network.responses

import com.sabeeldev.myapplication.data.network.model.Coins

class CoinsListResponse(
    val success: String? = null,
    val crypto: HashMap<String, Coins>?=null
) {
}