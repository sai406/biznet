package com.mstech.gamesnatcherz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.PartnerDetailsActicity;
import com.mstech.gamesnatcherz.model.RestaurentHistoryResponse;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class SliderAdapt extends SliderViewAdapter<SliderAdapt.SliderAdapterViewHolder> {

    // list for storing urls of images.
    private final List<RestaurentHistoryResponse> mSliderItems;
    private final Context context;

    // Constructor
    public SliderAdapt(Context context, List<RestaurentHistoryResponse> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.context = context;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final RestaurentHistoryResponse sliderItem = mSliderItems.get(position);

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getBusinessLogoPath())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
        viewHolder.name.setText(sliderItem.getBusinessName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(
                        new Intent(context, PartnerDetailsActicity.class).putExtra("data", sliderItem));
            }
        });
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    // this method will return
    // the count of our list.


    static class SliderAdapterViewHolder extends ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground;
        TextView name;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.catimage);
            name = itemView.findViewById(R.id.name);
            this.itemView = itemView;
        }
    }

}
