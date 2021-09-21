package com.sabeeldev.myapplication.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sabeeldev.myapplication.ui.Fragments.EurFragment
import com.sabeeldev.myapplication.ui.Fragments.PkrFragment
import com.sabeeldev.myapplication.ui.Fragments.UsdFragment

class TabsAdapter(
    private val myContext: Context,
    fm: FragmentManager,
    internal var totalTabs: Int
) : FragmentPagerAdapter(fm) {

    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return UsdFragment("USD")
            }
            1 -> {
                return EurFragment("EUR")
            }
            2 -> {
                return PkrFragment("PKR")
            }
        }
        return UsdFragment("USD")
    }

    // this counts total number of tabs
    override fun getCount(): Int {
        return totalTabs
    }
}