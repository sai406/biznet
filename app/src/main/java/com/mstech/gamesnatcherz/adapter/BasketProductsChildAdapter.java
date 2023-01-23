package com.mstech.gamesnatcherz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.Basketproducts;
import com.mstech.gamesnatcherz.model.OrderItem;
import com.squareup.picasso.Picasso;

import java.util.List;

/** HARISH GADDAM */

public class BasketProductsChildAdapter extends RecyclerView.Adapter<BasketProductsChildAdapter.MyViewHolder> {

    private Context mContext;
    List<OrderItem> arrayList;
    Basketproducts orderitem;

    public BasketProductsChildAdapter(Context mContext, List<OrderItem> arrayList, Basketproducts responseBasketProductOrders) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.orderitem = responseBasketProductOrders;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_basket_products_child, parent, false);
        mContext = view.getContext();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        OrderItem basketOrderItem = arrayList.get(position);

        if (!basketOrderItem.getProductImagePath().isEmpty()) {
//            Picasso.with(mContext).load(basketOrderItem.getProductImagePath()).into(holder.ivPic);
            Glide.with(mContext).load(basketOrderItem.getProductImagePath()).into(holder.ivPic);
        }

        holder.tvProductName.setText((basketOrderItem.getProductName().isEmpty()) ? "" : arrayList.get(position).getProductName());
        holder.tvProductSize.setText((basketOrderItem.getDealAttributeValues().isEmpty()) ? "" : arrayList.get(position).getDealAttributeValues());
        holder.tvQtyValue.setText(String.valueOf((basketOrderItem.getQty() == 0) ? "" : basketOrderItem.getQty()));
        holder.tvPriceValue.setText((basketOrderItem.getDisplayPrice().isEmpty()) ? "" : arrayList.get(position).getDisplayPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mContext.startActivity(new Intent(mContext, OrderDetailsActivity.class).putExtra("data", orderitem));
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivPic;
        TextView tvProductName;
        TextView tvProductSize;
        TextView tvQtyValue;
        TextView tvPriceValue;
        TextView tvStatusValue;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.ivPic);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductSize = itemView.findViewById(R.id.tvProductSize);
            tvQtyValue = itemView.findViewById(R.id.tvQtyValue);
            tvPriceValue = itemView.findViewById(R.id.tvPriceValue);
            tvStatusValue = itemView.findViewById(R.id.tvStatusValue);
        }
    }
}
