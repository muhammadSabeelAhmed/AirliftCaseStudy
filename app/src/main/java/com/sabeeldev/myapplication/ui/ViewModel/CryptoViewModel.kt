package com.sabeeldev.myapplication.ui.ViewModel

import androidx.lifecycle.ViewModel
import com.sabeeldev.myapplication.Util.APIException
import com.sabeeldev.myapplication.Util.Coroutines
import com.sabeeldev.myapplication.Util.toast
import com.sabeeldev.myapplication.data.repositories.UserRepository

class CryptoViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var productsListner: CryptoListner? = null

    // fun getLocalProducts() = repository.getLocalProducts()

    fun fetchCryptoData(target: String) {
        productsListner?.onStarted()
        Coroutines.main {
            try {
                val response = repository.getCryptoData(target)
                response.let {
                    productsListner?.onSuccess(it)
                    return@main
                }
            } catch (e: APIException) {
                productsListner?.onFailure(e.message!!)

            }
        }
    }


    fun fetchCoinsList() {
        productsListner?.onStarted()
        Coroutines.main {
            try {
                val response = repository.getCoinsList()
                response.let {
                    productsListner?.onSuccess(it)
                    return@main
                }
                // productsListner?.onFailure(response.message!!)
            } catch (e: APIException) {
                productsListner?.onFailure(e.message!!)

            }
        }
    }

    fun fetchConversionData(amount: Int, to: String?, from: String?) {
        productsListner?.onStarted()
        Coroutines.main {
            try {
                val response = repository.getConversion(from, to, amount)
                response.let {
                    productsListner?.onConversion(it)
                    return@main
                }
            } catch (e: APIException) {
                productsListner?.onFailure(e.message!!)
            }
        }
    }
}