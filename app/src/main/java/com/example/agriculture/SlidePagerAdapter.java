package com.example.agriculture;

// SlidePagerAdapter.java

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SlidePagerAdapter extends FragmentPagerAdapter {

    private static final int[] IMAGES = {R.drawable.g1, R.drawable.g2, R.drawable.g3};

    public SlidePagerAdapter(FragmentManager fm) {
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
