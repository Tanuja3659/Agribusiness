package com.example.agriculture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class SlideFragment extends Fragment {

    private int imageResource;

    public SlideFragment() {
        // Required empty public constructor
    }

    public static SlideFragment newInstance(int imageResource) {
        SlideFragment fragment = new SlideFragment();
        Bundle args = new Bundle();
        args.putInt("imageResource", imageResource);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageResource = getArguments().getInt("imageResource");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.slide_layout, container, false);
        ImageView slideImage = view.findViewById(R.id.slideImage);
        slideImage.setImageResource(imageResource);
        return view;
    }
}
