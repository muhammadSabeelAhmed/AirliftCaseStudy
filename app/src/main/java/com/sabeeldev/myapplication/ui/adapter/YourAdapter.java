package com.sabeeldev.myapplication.ui.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.sabeeldev.myapplication.ui.Fragments.EurFragment;
import com.sabeeldev.myapplication.ui.Fragments.PkrFragment;
import com.sabeeldev.myapplication.ui.Fragments.UsdFragment;

public class YourAdapter extends FragmentPagerAdapter {
    Fragment screens[];

    public YourAdapter(FragmentManager fm) {
        super(fm);
        screens = new Fragment[3];
        screens[0] = new UsdFragment("USD");
        screens[1] = new EurFragment("EUR");
        screens[2] = new PkrFragment("PKR");
    }

    @Override
    public Fragment getItem(int index) {
        if (index <= screens.length) {
            return screens[index];
        }
        return null;
    }

    @Override
    public int getCount() {
        return screens.length;
    }

}
