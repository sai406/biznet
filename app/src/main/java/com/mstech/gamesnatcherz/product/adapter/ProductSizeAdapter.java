package com.mstech.gamesnatcherz.product.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.products.AttributeValue;
import com.mstech.gamesnatcherz.product.activity.onClickInterface;

import java.util.ArrayList;

/**
 * HARISH GADDAM
 */

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolerRoster> {

    Context mContext;
    private ArrayList<AttributeValue> arrayListProductSize = new ArrayList<>();
    int row_index = -1;
    private com.mstech.gamesnatcherz.product.activity.onClickInterface onClickInterface;

    public ProductSizeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ProductSizeAdapter(Context mContext, ArrayList<AttributeValue> arrayList, onClickInterface onClickInterface) {
        this.mContext = mContext;
        this.arrayListProductSize = arrayList;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public ViewHolerRoster onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_product_sizes, parent, false);
        mContext = view.getContext();
        return new ViewHolerRoster(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolerRoster holder, int position) {

        holder.tvProductSize.setText(!(arrayListProductSize.get(position).getAttributeValue().isEmpty()) ? arrayListProductSize.get(position).getAttributeValue() : "");

        holder.tvProductSize.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();
        });
        if(row_index==position) {
            holder.tvProductSize.setBackgroundColor(Color.parseColor("#000000"));
            holder.tvProductSize.setTextColor(Color.parseColor("#ffffff"));
            onClickInterface.setClick(position);
        }
        else
        {
            holder.tvProductSize.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tvProductSize.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return arrayListProductSize == null ? 0 : arrayListProductSize.size();
    }

    public class ViewHolerRoster extends RecyclerView.ViewHolder {
        private TextView tvProductSize;
        public ViewHolerRoster(@NonNull View itemView) {
            super(itemView);
            tvProductSize = itemView.findViewById(R.id.tvProductSize);
        }
    }
}