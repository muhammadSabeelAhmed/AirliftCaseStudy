package com.sabeeldev.myapplication.data.db.entities

import androidx.room.Entity

@Entity
data class CryptoData(
    val name: String? = null,
    val rate: Double? = null,
    val icon_url: String? = null,
    val high: Double? = null,
    val low: Double? = null,
    val vol: Double? = null,
    val cap: Double? = null,
    val sup: Double? = null,
    val change: Double? = null,
    val change_pct: Double? = null,
) {

}