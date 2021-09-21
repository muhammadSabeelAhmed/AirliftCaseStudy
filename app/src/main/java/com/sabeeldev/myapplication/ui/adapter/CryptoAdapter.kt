package com.sabeeldev.myapplication.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sabeeldev.myapplication.R
import com.sabeeldev.myapplication.Util.listen
import com.sabeeldev.myapplication.data.network.model.Rates
import com.sabeeldev.myapplication.data.network.model.Coins
import com.sabeeldev.myapplication.ui.activities.ConversionActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_item.view.*
import java.math.BigDecimal


class CryptoAdapter(
    var coinValue: ArrayList<Rates>? = null,
    var coinsKey: ArrayList<String>? = null,
    var targetType: String? = null,
    var getIcons: HashMap<String, Coins>
) :
    RecyclerView.Adapter<CryptoAdapter.ProductsViewHolder>() {

    fun updateCryptoItems(
        updateValue: ArrayList<Rates>? = null,
        updateKey: ArrayList<String>? = null
    ) {
        coinsKey = updateKey
        coinValue = updateValue
        notifyDataSetChanged()
    }


    fun launchNextScreen(context: Context, coin: String, type: String?): Intent {
        val intent = Intent(context, ConversionActivity::class.java)
        intent.putExtra("coin", coin)
        intent.putExtra("type", type)
        return intent
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {

        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.product_item, parent, false)

        return ProductsViewHolder(cellForRow).listen { pos, type ->
            parent.context.startActivity(
                launchNextScreen(
                    parent.context,
                    coinsKey?.get(pos).toString(),
                    targetType
                )
            )
        }

    }


    override fun getItemCount(): Int = coinValue?.size!!


    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(coinValue?.get(position), coinsKey?.get(position), targetType, getIcons)
    }


    class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val preview = view.coin_icon
        private val title = view.title
        private val price = view.price
        private val low = view.low
        private val high = view.high

        fun bind(
            details: Rates?,
            coinTitle: String?,
            targetType: String?,
            icons: HashMap<String, Coins>
        ) {
            Picasso.get().load(icons.get(coinTitle)?.icon_url).into(preview)
            title.text = "${coinTitle}/${targetType}"
            converBigDecimal(details?.rate, price)
            converBigDecimal(details?.low, low)
            converBigDecimal(details?.high, high)
        }

        fun converBigDecimal(double: Double?, view: TextView) {
            view.text =
                "${double?.let { BigDecimal(it.toString()).setScale(5, BigDecimal.ROUND_HALF_UP) }}"
        }
    }
}

