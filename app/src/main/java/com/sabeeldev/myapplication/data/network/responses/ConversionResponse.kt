package com.sabeeldev.myapplication.data.network.responses

import com.sabeeldev.myapplication.data.network.model.Error

class ConversionResponse(
    val success: Boolean? = null,
    val error: Error? = null
) {
}