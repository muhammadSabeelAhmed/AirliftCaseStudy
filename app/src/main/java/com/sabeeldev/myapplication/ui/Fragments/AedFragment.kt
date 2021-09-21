package com.sabeeldev.myapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.sabeeldev.myapplication.R
import com.sabeeldev.myapplication.Util.*
import com.sabeeldev.myapplication.data.network.MyApi
import com.sabeeldev.myapplication.data.network.responses.CoinsListResponse
import com.sabeeldev.myapplication.data.network.responses.ConversionResponse
import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse
import com.sabeeldev.myapplication.data.repositories.UserRepository
import com.sabeeldev.myapplication.databinding.FragmentGeneralBinding
import com.sabeeldev.myapplication.ui.ViewModel.CryptoListner
import com.sabeeldev.myapplication.ui.ViewModel.CryptoViewModel
import com.sabeeldev.myapplication.ui.ViewModel.ProductsViewModelFactory
import com.sabeeldev.myapplication.ui.adapter.CryptoAdapter

class AedFragment(var type: String) : Fragment(), CryptoListner {

    private lateinit var binding: FragmentGeneralBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_general, container, false)
        val view = binding.root
        type = "EUR"
        binding.recyclerView.layoutManager = GridLayoutManager(view.context, 1)

        binding.shimmerView.startShimmerAnimation()
        val api = MyApi()
        val repository = UserRepository(api)
        val factory = ProductsViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(CryptoViewModel::class.java)
        viewModel.productsListner = this
        if (aedList.size == 0) {
            viewModel.fetchCryptoData(type)
        } else {
            setupdata()
        }
        return view
    }

    fun setupdata() {
        binding.shimmerView.stopShimmerAnimation()
        binding.shimmerView.visibility = View.GONE
        binding.recyclerView.adapter = CryptoAdapter(
            eurList,
            listNames,
            type,
            getCoinList()
        )
    }

    override fun onStarted() {
    }

    override fun onConversion(response: ConversionResponse?) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(response: CryptoRatesResponse?) {
        aedList = ArrayList(response?.rates?.values)
        listNames = ArrayList(response?.rates?.keys)
        if (response?.target.equals(type)) {
            setupdata()
        }
    }

    override fun onSuccess(response: CoinsListResponse?) {
        TODO("Not yet implemented")
    }

    override fun onFailure(message: String) {
        context?.toast(message)
        TODO("Not yet implemented")
    }
}