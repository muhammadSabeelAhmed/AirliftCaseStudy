package com.sabeeldev.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sabeeldev.myapplication.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.sabeeldev.myapplication.Util.coinsList
import com.sabeeldev.myapplication.Util.listNames
import com.sabeeldev.myapplication.Util.toast
import com.sabeeldev.myapplication.data.network.MyApi
import com.sabeeldev.myapplication.data.network.responses.CoinsListResponse
import com.sabeeldev.myapplication.data.network.responses.ConversionResponse
import com.sabeeldev.myapplication.data.network.responses.CryptoRatesResponse
import com.sabeeldev.myapplication.data.repositories.UserRepository
import com.sabeeldev.myapplication.databinding.ActivityConversionBinding
import com.sabeeldev.myapplication.ui.ViewModel.CryptoListner
import com.sabeeldev.myapplication.ui.ViewModel.CryptoViewModel
import com.sabeeldev.myapplication.ui.ViewModel.ProductsViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_conversion.*
import kotlinx.android.synthetic.main.header.*


class ConversionActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, CryptoListner {

    lateinit var binding: ActivityConversionBinding
    private var type: String? = null
    private var name: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_conversion)

        val api = MyApi()
        val repository = UserRepository(api)
        val factory = ProductsViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(CryptoViewModel::class.java)
        viewModel.productsListner = this

        val intent = intent
        type = intent.getStringExtra("type")
        name = intent.getStringExtra("coin")
        initData()
        back_icon.setImageDrawable(getDrawable(R.drawable.back_icon))
        heading.setText("${name}/${type}")

        back_icon.setOnClickListener {
            onBackPressed()
        }
        button.setOnClickListener {
            val amoun: String = et_amount.text.toString()
            val to: String = to_tv.text.toString()
            val from: String = selected_tv.text.toString()
            if (amoun.isEmpty()) {
                toast("Please add all the details")
            } else if (from.isEmpty() || to.isEmpty() || amoun.toInt() <= 0) {
                toast("Please add all the details")
            } else {
                viewModel.fetchConversionData(amoun.toInt(), to, name)
            }
        }

    }

    private fun initData() {
        selected_tv.setText("${name}/${type}")
        Picasso.get().load(coinsList.get(name)?.icon_url).into(selected_iv)

        var aa = ArrayAdapter(this, R.layout.spinner_right_aligned, listNames)
        aa.setDropDownViewResource(R.layout.spinner_right_aligned)
        to_spinner!!.setAdapter(aa)
        to_spinner!!.setOnItemSelectedListener(this)
        with(to_spinner)
        {
            adapter = aa
            setSelection(0, false)
            prompt = "Select your preferred Currency"
            setPopupBackgroundResource(R.color.material_grey_600)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        to_spinner.setSelection(p2)
        to_tv.text = listNames.get(p2)
        Picasso.get().load(coinsList.get(listNames.get(p2))?.icon_url).into(to_iv)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onStarted() {
        button.setClickable(false);
    }

    override fun onConversion(response: ConversionResponse?) {
        button.setClickable(true);
        toast(response?.error?.info.toString())
    }

    override fun onSuccess(response: CryptoRatesResponse?) {
        button.setClickable(true);
    }

    override fun onSuccess(response: CoinsListResponse?) {
        button.setClickable(true);
    }

    override fun onFailure(message: String) {
        button.setClickable(true);
        toast(message)
    }


}