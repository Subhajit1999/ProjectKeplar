package com.subhajitkar.commercial.project_keplar.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.subhajitkar.commercial.project_keplar.R;
import com.subhajitkar.commercial.project_keplar.utils.FeaturedSliderAdapter;
import com.subhajitkar.commercial.project_keplar.utils.SliderItem;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements FeaturedSliderAdapter.SliderItemClickListener {
    private static final String TAG = "HomeFragment";

    private SliderView featuredSlider;
    private FeaturedSliderAdapter sliderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //methods callback
        setUpSlider(view);
    }

    private void setUpSlider(View view){
        Log.d(TAG, "setUpSlider: setting up the featured slider");

        //sliderView init and setUp
        featuredSlider = view.findViewById(R.id.featured_slider);
        sliderAdapter = new FeaturedSliderAdapter(getContext());
        //adding items to the slider
        sliderAdapter.addItem(new SliderItem(R.drawable.trending_repos, "Trending Repositories"));
        sliderAdapter.addItem(new SliderItem(R.drawable.trending_devs, "Trending Developers"));
        //setUp
        sliderAdapter.setOnSliderItemClickListener(this);
        featuredSlider.setSliderAdapter(sliderAdapter);
        //available IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        featuredSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        featuredSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        featuredSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        featuredSlider.setIndicatorSelectedColor(Color.WHITE);
        featuredSlider.setIndicatorUnselectedColor(getResources().getColor(R.color.light_yellow));
        featuredSlider.setScrollTimeInSec(3);
        featuredSlider.setAutoCycle(true);
        featuredSlider.startAutoCycle();

        featuredSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Toast.makeText(getContext(), "Item clicked at position: "+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onSliderItemClick(int position) {
        Toast.makeText(getContext(), "Item clicked at position: "+position, Toast.LENGTH_SHORT).show();
    }
}