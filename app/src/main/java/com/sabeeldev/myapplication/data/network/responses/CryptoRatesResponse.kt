package com.sabeeldev.myapplication.data.network.responses

import androidx.room.PrimaryKey
import com.sabeeldev.myapplication.data.network.model.Rates


data class CryptoRatesResponse(
    var success: String? = null,
    var terms: String? = null,
    var privacy: String? = null,
    var timestamp: Int? = null,
    var target: String? = null,
    var rates: HashMap<String, Rates>? = null
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}