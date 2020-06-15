package com.subhajitkar.commercial.project_keplar.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.subhajitkar.commercial.project_keplar.R;

import java.util.ArrayList;
import java.util.List;

public class FeaturedSliderAdapter extends
        SliderViewAdapter<FeaturedSliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<SliderItem> mSliderItems = new ArrayList<>();
    private SliderItemClickListener mListener;

    public FeaturedSliderAdapter(Context context) {
        this.context = context;
    }

    public void setOnSliderItemClickListener(SliderItemClickListener listener){
        mListener = listener;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = mSliderItems.get(position);
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getItemImage())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        viewHolder.textDescription.setText(sliderItem.getItemDescript());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != RecyclerView.NO_POSITION){
                    mListener.onSliderItemClick(position);  //item click interface method callback
                }
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mSliderItems.size();
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        TextView textDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_slider_item);
            textDescription = itemView.findViewById(R.id.tv_slider_desc);
            this.itemView = itemView;
        }
    }
public interface SliderItemClickListener{
        void onSliderItemClick(int position);
    }
}
