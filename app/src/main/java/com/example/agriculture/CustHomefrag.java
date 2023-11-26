// HomeFragment.java
package com.example.agriculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

public class CustHomefrag extends Fragment {

    private ViewPager viewPager;
    private SlidePagerAdapter1 slidePagerAdapter;

    public CustHomefrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        slidePagerAdapter = new SlidePagerAdapter1(getChildFragmentManager());
        viewPager.setAdapter(slidePagerAdapter);

        return view;
    }
}
