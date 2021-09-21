package com.sabeeldev.myapplication.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.sabeeldev.myapplication.R
import com.sabeeldev.myapplication.databinding.ActivityHomeBinding
import com.sabeeldev.myapplication.ui.adapter.TabsAdapter
import com.sabeeldev.myapplication.ui.adapter.YourAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.header.*

class HomeActivity : AppCompatActivity() {
    var binding: ActivityHomeBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        initTabLayout()
    }

    override fun onResume() {
        back_icon.setImageDrawable(getDrawable(R.drawable.square_icon))
        heading.setText(getString(R.string.app_name))
        super.onResume()
    }

    private fun initTabLayout() {
        tabLayout!!.addTab(tabLayout!!.newTab().setText("USD"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("EUR"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("PKR"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = TabsAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
//        viewPager!!.adapter = adapter
        viewPager!!.adapter = YourAdapter(super.getSupportFragmentManager())


        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

    }
}