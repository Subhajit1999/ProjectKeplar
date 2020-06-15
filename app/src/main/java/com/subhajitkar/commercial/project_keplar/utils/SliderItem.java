package com.subhajitkar.commercial.project_keplar.utils;

public class SliderItem {

    private int mItemImage;
    private String mItemDescript;

    public SliderItem(int itemImage, String itemDesc){
        mItemImage = itemImage;
        mItemDescript= itemDesc;
    }

    public int getItemImage() {
        return mItemImage;
    }

    public String getItemDescript() {
        return mItemDescript;
    }
}
