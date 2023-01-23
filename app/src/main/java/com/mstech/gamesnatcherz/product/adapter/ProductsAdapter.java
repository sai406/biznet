package com.mstech.gamesnatcherz.product.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.products.ResponseGETProduct;
import com.mstech.gamesnatcherz.product.activity.ProductDetailsPassObjectActivity;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * HARISH GADDAM
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolerRoster> {

    Context mContext;
    ArrayList<ResponseGETProduct> arrayList;

    public ProductsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ProductsAdapter(Context mContext, ArrayList<ResponseGETProduct> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolerRoster onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_products, parent, false);
        mContext = view.getContext();

        return new ViewHolerRoster(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolerRoster holder, int position) {

        ResponseGETProduct product = arrayList.get(position);
        Picasso.get().load(arrayList.get(position).getProductImagePath()).placeholder(R.drawable.ic_loading).error(R.drawable.ic_loading).into(holder.ivPic);
        holder.tvTitle.setText(!(arrayList.get(position).getProductName().isEmpty()) ? arrayList.get(position).getProductName() : "");
        holder.tvDescription.setText(!(arrayList.get(position).getDescription().isEmpty()) ? arrayList.get(position).getDescription() : "");

        holder.tvPriceStrike.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.tvPriceStrike.setText(!(arrayList.get(position).getDisplayprice().isEmpty()) ? arrayList.get(position).getDisplayprice() : "");
        holder.tvPrice.setText(!(arrayList.get(position).getDisplayofferprice().isEmpty()) ? arrayList.get(position).getDisplayofferprice() : "");

        holder.cvProducts.setOnClickListener(view -> {
            //This is used for pass object to ProductDetailsPassObjectActivity.
            Intent intent = new Intent(mContext, ProductDetailsPassObjectActivity.class);
            intent.putExtra("product_details", product);
            mContext.startActivity(intent);

            /*//This is used for pass productId to ProductDetailsActivity.
            Intent intent = new Intent(mContext, ProductDetailsActivity.class);
            intent.putExtra("productId", product.getProductId());
            mContext.startActivity(intent);*/
        });
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
//        return 10;
    }

    public class ViewHolerRoster extends RecyclerView.ViewHolder {

        private final CardView cvProducts;
        private final ImageView ivPic;
        private final TextView tvTitle;
        private final TextView tvDescription;
        private final TextView tvPriceStrike;
        private final TextView tvPrice;

        public ViewHolerRoster(@NonNull View itemView) {
            super(itemView);

            cvProducts = itemView.findViewById(R.id.cvProducts);
            ivPic = itemView.findViewById(R.id.ivPic);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvPriceStrike = itemView.findViewById(R.id.tvPriceStrike);
        }
    }

    public void setFilter(ArrayList<ResponseGETProduct> filterdNames) {
        this.arrayList = filterdNames;
        notifyDataSetChanged();
    }
}