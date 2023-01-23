package com.mstech.gamesnatcherz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.Basketproducts;

import java.util.ArrayList;

/**
 * HARISH GADDAM
 */

public class BasketProductsAdapter extends RecyclerView.Adapter<BasketProductsAdapter.ViewHolerRoster> {

    Context mContext;
    private final RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final ArrayList<Basketproducts> arrayListBasketRoot;

    public BasketProductsAdapter(Context mContext, ArrayList<Basketproducts> arrayListBasketRoot) {
        this.mContext = mContext;
        this.arrayListBasketRoot = arrayListBasketRoot;
    }

    @NonNull
    @Override
    public ViewHolerRoster onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_basket_products, parent, false);
        mContext = view.getContext();
        return new ViewHolerRoster(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolerRoster holder, int position) {

        Basketproducts item = arrayListBasketRoot.get(position);

        holder.tvOrderDateValue.setText((arrayListBasketRoot.get(position).getOrderDateDisplay().isEmpty()) ? "" : arrayListBasketRoot.get(position).getOrderDateDisplay());
//            holder.tvStatusValue.setText((item.getStatus()));
//            if (item.getStatusDisplay().equalsIgnoreCase("Completed")) {
//                holder.tvStatusValue.setTextColor(ContextCompat.getColor(mContext, R.color.green));
//            } else if (item.getStatusDisplay().equalsIgnoreCase("Processing")) {
//                holder.tvStatusValue.setTextColor(ContextCompat.getColor(mContext, R.color.red));
//            } else if (item.getStatusDisplay().equalsIgnoreCase("Pending")) {
//                holder.tvStatusValue.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
//            }
        // Create layout manager with initial prefetch item count
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvBasketOrderItem.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );

        layoutManager.setInitialPrefetchItemCount(item.getOrderItems().size());

        // Create sub item view adapter
        BasketProductsChildAdapter subItemAdapter = new BasketProductsChildAdapter(mContext, arrayListBasketRoot.get(position).getOrderItems(), arrayListBasketRoot.get(position));
        holder.rvBasketOrderItem.setLayoutManager(layoutManager);
        holder.rvBasketOrderItem.setAdapter(subItemAdapter);
        holder.rvBasketOrderItem.setRecycledViewPool(viewPool);
        subItemAdapter.notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return arrayListBasketRoot == null ? 0 : arrayListBasketRoot.size();
    }

    public class ViewHolerRoster extends RecyclerView.ViewHolder {

        TextView tvOrderDateValue;
        TextView tvStatusValue;
        RecyclerView rvBasketOrderItem;

        public ViewHolerRoster(@NonNull View itemView) {
            super(itemView);

            tvOrderDateValue = itemView.findViewById(R.id.tvOrderDateValue);
            rvBasketOrderItem = itemView.findViewById(R.id.rvBasketOrderItem);
            tvStatusValue = itemView.findViewById(R.id.tvStatusValue);
        }
    }
}