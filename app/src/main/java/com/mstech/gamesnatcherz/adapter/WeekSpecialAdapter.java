package com.mstech.gamesnatcherz.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.products.ResponseGETProduct;
import com.mstech.gamesnatcherz.product.activity.ProductDetailsPassObjectActivity;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.io.Serializable;
import java.util.List;

public class WeekSpecialAdapter extends SliderViewAdapter<WeekSpecialAdapter.SliderAdapterViewHolder> {

    // list for storing urls of images.
    private final List<ResponseGETProduct> mSliderItems;
    private final Context context;

    // Constructor
    public WeekSpecialAdapter(Context context, List<ResponseGETProduct> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
        this.context = context;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_ofers_item, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final ResponseGETProduct item = mSliderItems.get(position);

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
                .load(item.getProductImagePath())
                .fitCenter()
                .into(viewHolder.goodsimage);
//        viewHolder.name.setText(sliderItem.getDealCatName());

        viewHolder.goodsname.setText(item.getProductName());
        viewHolder.goodsdesc.setText(item.getDescription());
        viewHolder.goodprice.setText(item.getDisplayprice());
        if (item.getPrice().equals("$0.00") || item.getPrice().equals("$0")) {
            viewHolder.goodprice.setText("Enquiry");
            viewHolder.goodprice.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
//        if (item.getBusinessType().equals("5")) {
//            viewHolder.goodprice.setText("Donate");
//            viewHolder.goodprice.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//        }

        viewHolder.goodsdealprice.setText(item.getDisplayofferprice());
        viewHolder.goodprice.setPaintFlags(viewHolder.goodprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        if (item.getPrice().equals(item.getOfferPrice())) {
            viewHolder.goodsdealprice.setVisibility(View.INVISIBLE);
        } else {
            viewHolder.goodsdealprice.setVisibility(View.VISIBLE);
        }
//        Picasso.with(context).load(item.getDealimag()).into(viewHolder.goodsimage);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsPassObjectActivity.class);
                intent.putExtra("product_details", item);
                context.startActivity(intent);
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
        ImageView goodsimage;
        Button addtobasket;
        TextView goodsname, goodsdesc, goodprice, goodsdealprice;

        public SliderAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsimage = itemView.findViewById(R.id.imageviewgood);
            goodsname = itemView.findViewById(R.id.goodnametvid);
            goodsdesc = itemView.findViewById(R.id.gooddesctvid);
            goodprice = itemView.findViewById(R.id.pricetvid);
            goodsdealprice = itemView.findViewById(R.id.dealingprice);
        }
    }

}
