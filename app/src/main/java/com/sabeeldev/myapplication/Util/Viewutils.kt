package com.sabeeldev.myapplication.Util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sabeeldev.myapplication.data.network.model.Rates
import com.sabeeldev.myapplication.data.network.model.Coins
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

var usdList = ArrayList<Rates>()
var pkrList = ArrayList<Rates>()
var aedList = ArrayList<Rates>()
var eurList = ArrayList<Rates>()
var listNames = ArrayList<String>()
var coinsList = HashMap<String, Coins>()


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.snackBar(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).also { snackbar ->
        snackbar.setAction("Ok") {
            snackbar.dismiss()
        }
    }.show()
}

fun getApiKey(): String {
    return "309847a8f0698e596e6ae19d1611fc83"
}

fun ProgressBar.showProgress() {
    visibility = View.VISIBLE
}

fun ProgressBar.hideProgress() {
    visibility = View.GONE
}

fun setCrypto(_listCrypto: HashMap<String, Rates>?, type: String) {
    if (type === "USD") {
        usdList.clear()
        listNames = ArrayList(_listCrypto?.keys)
        usdList = ArrayList(_listCrypto?.values)
    } else if (type === "EUR") {
        eurList.clear()
        eurList = ArrayList(_listCrypto?.values)
    } else if (type === "PKR") {
        pkrList.clear()
        pkrList = ArrayList(_listCrypto?.values)
    } else if (type === "AED") {
        aedList.clear()
        aedList = ArrayList(_listCrypto?.values)
    }
}

fun setCoinList(_listCoins: HashMap<String, Coins>) {
    coinsList.clear()
    coinsList = _listCoins

}


fun getCoinList(): HashMap<String, Coins> {
    return coinsList
}

fun getCryptoValues(): ArrayList<Rates> {
    return usdList
}

fun getCryptoNames(): ArrayList<String> {
    return listNames
}

fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
    itemView.setOnClickListener {
        event.invoke(getAdapterPosition(), getItemViewType())
    }
    return this
}


fun hasActiveInternetConnection(context: Context?): Boolean {
    if (isNetworkAvailable(context)) {
        try {
            val urlc: HttpURLConnection =
                URL("http://www.google.com").openConnection() as HttpURLConnection
            urlc.setRequestProperty("User-Agent", "Test")
            urlc.setRequestProperty("Connection", "close")
            urlc.setConnectTimeout(1500)
            urlc.connect()
            return urlc.getResponseCode() == 200
        } catch (e: IOException) {
            context?.toast("Error checking internet connection")
        }
    } else {
        context?.toast("No network available!")
    }
    return false
}

private fun isNetworkAvailable(context: Context?): Boolean {
    val connectivityManager =
        context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null
}
