package com.mstech.gamesnatcherz.product.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.CartItem;
import com.mstech.gamesnatcherz.utils.SharePref;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class FinalOrderAdapter extends RecyclerView.Adapter<FinalOrderAdapter.MyViewHolder> {

    Context mContext;
    private ArrayList<CartItem> arrayListCartItems = new ArrayList<>();
    ProgressDialog mProgressDialog;
    int present_count = 1;
    double price = 0;
    SharePref sharePref;

    int totalQty = 0;
    int orgId = 0;
    String totalDisplayPrice = "";

    public FinalOrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public FinalOrderAdapter(Context mContext, int orgId, int totalQty, String totalDisplayPrice, ArrayList<CartItem> arrayListCartItems) {
        this.mContext = mContext;
        this.arrayListCartItems = arrayListCartItems;
        this.orgId = orgId;
        this.totalQty = totalQty;
        this.totalDisplayPrice = totalDisplayPrice;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_order_layout, parent, false);
        mContext = view.getContext();
        sharePref = new SharePref(mContext);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final CartItem cartItem = arrayListCartItems.get(position);
        if (!cartItem.getProductImagePath().isEmpty()) {
            Picasso.get().load(cartItem.getProductImagePath()).placeholder(R.drawable.ic_loading).error(R.drawable.ic_loading).into(holder.ivPic);
        }
        holder.tvProductName.setText((cartItem.getProductName().isEmpty()) ? "" : cartItem.getProductName());
        holder.tvProductSize.setText((cartItem.getDealAttributeValues().isEmpty()) ? "" : cartItem.getDealAttributeValues());
        holder.tvPrice.setText((cartItem.getDisplayPrice().isEmpty()) ? "" : cartItem.getDisplayPrice());
        holder.tvCartItemCount.setText("Quantity : "+((cartItem.getQty() <= 0) ? 0 : cartItem.getQty()));

    }

    @Override
    public int getItemCount() {
        return arrayListCartItems == null ? 0 : arrayListCartItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cvBackground;
        public CardView cvForeground;

        ImageView ivPic;
        ImageView ivMinus;
        ImageView ivAdd;
        ConstraintLayout cLayoutUpdateCartItems;

        TextView tvProductName;
        TextView tvProductSize;
        TextView tvPrice;
        TextView tvCartItemCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cvBackground = itemView.findViewById(R.id.cvBackground);
            cvForeground = itemView.findViewById(R.id.cvForeground);
            ivPic = itemView.findViewById(R.id.ivPic);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductSize = itemView.findViewById(R.id.tvProductSize);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCartItemCount = itemView.findViewById(R.id.tvCartItemCount);
            cLayoutUpdateCartItems = itemView.findViewById(R.id.cLayoutUpdateCartItems);
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}