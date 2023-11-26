package com.example.agriculture;

// SlidePagerAdapter.java

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SlidePagerAdapter1 extends FragmentPagerAdapter {

    private static final int[] IMAGES = {R.drawable.c1, R.drawable.c2, R.drawable.c3};

    public SlidePagerAdapter1(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SlideFragment.newInstance(IMAGES[position]);
    }

    @Override
    public int getCount() {
        return IMAGES.length;
    }
}
