package com.sabeeldev.myapplication.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sabeeldev.myapplication.R
import com.sabeeldev.myapplication.Util.setCoinList
import com.sabeeldev.myapplication.Util.toast
import com.sabeeldev.myapplication.data.network.MyApi
import com.sabeeldev.myapplication.data.network.responses.CoinsListResponse
import com.sabeeldev.myapplication.data.network.responses.ConversionResponse
import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse
import com.sabeeldev.myapplication.data.repositories.UserRepository
import com.sabeeldev.myapplication.databinding.ActivitySplashBinding
import com.sabeeldev.myapplication.ui.ViewModel.CryptoListner
import com.sabeeldev.myapplication.ui.ViewModel.CryptoViewModel
import com.sabeeldev.myapplication.ui.ViewModel.ProductsViewModelFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import android.net.NetworkInfo

import android.net.ConnectivityManager
import com.sabeeldev.myapplication.Util.hasActiveInternetConnection


class SplashActivity : AppCompatActivity(), CryptoListner {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        val api = MyApi()
        val repository = UserRepository(api)
        val factory = ProductsViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(CryptoViewModel::class.java)
        viewModel.productsListner = this
        viewModel.fetchCoinsList()
    }

    override fun onStarted() {

    }

    override fun onConversion(response: ConversionResponse?) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: CryptoRatesResponse?) {

    }

    override fun onSuccess(response: CoinsListResponse?) {
        if (response?.crypto?.size!! > 0) {
            setCoinList(response.crypto)
            Intent(this, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

    override fun onFailure(message: String) {
        toast(message)
    }


}