package com.subhajitkar.commercial.project_keplar.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.subhajitkar.commercial.project_keplar.R;

import java.util.ArrayList;

import nl.psdcompany.duonavigationdrawer.views.DuoOptionView;

public class MenuAdapter extends BaseAdapter {
    private ArrayList<String> mOptions = new ArrayList<>();
    private ArrayList<DuoCustomOptionView> mOptionViews = new ArrayList<>();

    public MenuAdapter(ArrayList<String> options) {
        mOptions = options;
    }

    @Override
    public int getCount() {
        return mOptions.size();
    }

    @Override
    public Object getItem(int position) {
        return mOptions.get(position);
    }

    public void setViewSelected(int position, boolean selected) {

        // Looping through the options in the menu
        // Selecting the chosen option
        for (int i = 0; i < mOptionViews.size(); i++) {
            if (i == position) {
                mOptionViews.get(i).setSelected(selected);
            } else {
                mOptionViews.get(i).setSelected(!selected);
            }
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String option = mOptions.get(position);

        // Using the DuoOptionView to easily recreate the demo
        final DuoCustomOptionView optionView;
        if (convertView == null) {
            optionView = new DuoCustomOptionView(parent.getContext());
        } else {
            optionView = (DuoCustomOptionView) convertView;
        }

        // Using the DuoOptionView's default selectors
        optionView.bind(option, parent.getResources().getDrawable(R.drawable.duo_navigation_circle_black),
                parent.getResources().getDrawable(R.drawable.duo_shape_rectangle));

        // Adding the views to an array list to handle view selection
        mOptionViews.add(optionView);

        return optionView;
    }
}