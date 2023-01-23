package com.mstech.gamesnatcherz.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.mstech.gamesnatcherz.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/** HARISH GADDAM */

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
//    private Integer[] images = {R.drawable.banner, R.drawable.bg_btn_special_offers};
    ArrayList<String> images;

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public ViewPagerAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images == null ? 0 : images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slidingimages_layout, null);
        ImageView imageView = view.findViewById(R.id.image);

        Picasso.get().load(images.get(position)).placeholder(R.drawable.ic_loading).error(R.drawable.ic_loading).into(imageView);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}