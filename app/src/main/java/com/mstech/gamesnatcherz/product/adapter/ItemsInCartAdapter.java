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
import androidx.recyclerview.widget.RecyclerView;


import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.products.ResponseAddToCartItems;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.CartItem;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.RequestAddToCartItems;
import com.mstech.gamesnatcherz.product.activity.ItemsInCartActivity;
import com.mstech.gamesnatcherz.retro.ApiClient;
import com.mstech.gamesnatcherz.retro.ApiInterface;
import com.mstech.gamesnatcherz.utils.SharePref;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * HARISH GADDAM
 */

public class ItemsInCartAdapter extends RecyclerView.Adapter<ItemsInCartAdapter.MyViewHolder> {

    Context mContext;
    private ArrayList<CartItem> arrayListCartItems = new ArrayList<>();
    ProgressDialog mProgressDialog;
    int present_count = 1;
    double price = 0;
    SharePref sharePref;

    int totalQty = 0;
    int orgId = 0;
    String totalDisplayPrice = "";

    public ItemsInCartAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ItemsInCartAdapter(Context mContext, int orgId, int totalQty, String totalDisplayPrice, ArrayList<CartItem> arrayListCartItems) {
        this.mContext = mContext;
        this.arrayListCartItems = arrayListCartItems;
        this.orgId = orgId;
        this.totalQty = totalQty;
        this.totalDisplayPrice = totalDisplayPrice;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_swipe_to_delete, parent, false);
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
        holder.tvCartItemCount.setText(String.valueOf((cartItem.getQty() <= 0) ? 0 : cartItem.getQty()));

        holder.ivAdd.setOnClickListener(view -> {
            String presentValStr = holder.tvCartItemCount.getText().toString();
            present_count = Integer.parseInt(presentValStr);
            present_count++;
            price = (int) (present_count * cartItem.getProductPrice());
            holder.tvCartItemCount.setText(String.valueOf(present_count));
            holder.tvPrice.setText("$ " + price);

            /** API CALL ADD */
            if (isOnline()) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

                RequestAddToCartItems addToCartModel = new RequestAddToCartItems(
                        cartItem.getCartItemId(),
                        cartItem.getCartId(),
                        cartItem.getProductId(),
                        orgId,
                        sharePref.getMemberID(),
                        present_count,
                        String.valueOf(price),
                        cartItem.getDealAttributeValues(),
                        2
                );

                Call<ResponseAddToCartItems> call = apiInterface.apiPOSTAddRemoveProductCartItems(addToCartModel);
                call.enqueue(new Callback<ResponseAddToCartItems>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseAddToCartItems> call, @NonNull Response<ResponseAddToCartItems> response) {
                        Log.e(" add_quantity-->", "" + response.code());
                        assert response.body() != null;
                        if (response.body().getResult() > 0) {
                            Toast.makeText(mContext, "Updated Successfully!.", Toast.LENGTH_SHORT).show();
                            ((ItemsInCartActivity)mContext).apiCallGetCartItems();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseAddToCartItems> call, @NonNull Throwable t) {
                        Log.e(" onFailure-->", "" + t.getLocalizedMessage());
                    }
                });
            }
        });

        holder.ivMinus.setOnClickListener(view -> {
            String presentValStr = holder.tvCartItemCount.getText().toString();
            present_count = Integer.parseInt(presentValStr);
            if (presentValStr.equalsIgnoreCase(String.valueOf(Integer.parseInt("1")))) {
                Toast.makeText(mContext, "Can not Less than 1", Toast.LENGTH_LONG).show();
            } else {
                present_count--;
                price = (int) (present_count * cartItem.getProductPrice());
                holder.tvCartItemCount.setText(String.valueOf(present_count));
                holder.tvPrice.setText("$ " + price);

                /** API CALL MINUS */
                if (isOnline()) {
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

                    RequestAddToCartItems addToCartModel = new RequestAddToCartItems(
                            cartItem.getCartItemId(),
                            cartItem.getCartId(),
                            cartItem.getProductId(),
                            orgId,
                            sharePref.getMemberID(),
                            present_count,
                            String.valueOf(price),
                            cartItem.getDealAttributeValues(),
                            2
                    );

                    Call<ResponseAddToCartItems> call = apiInterface.apiPOSTAddRemoveProductCartItems(addToCartModel);
                    call.enqueue(new Callback<ResponseAddToCartItems>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseAddToCartItems> call, @NonNull Response<ResponseAddToCartItems> response) {
                            Log.e(" minus_quantity-->", "" + response.code());
                            assert response.body() != null;
                            if (response.body().getResult() > 0) {
                                Toast.makeText(mContext, "Removed Successfully!.", Toast.LENGTH_SHORT).show();
                                ((ItemsInCartActivity)mContext).apiCallGetCartItems();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseAddToCartItems> call, @NonNull Throwable t) {
                            Log.e(" onFailure-->", "" + t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
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

        TextView tvProductName;
        TextView tvProductSize;
        TextView tvPrice;
        TextView tvCartItemCount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cvBackground = itemView.findViewById(R.id.cvBackground);
            cvForeground = itemView.findViewById(R.id.cvForeground);
            ivPic = itemView.findViewById(R.id.ivPic);
            ivMinus = itemView.findViewById(R.id.ivMinus);
            ivAdd = itemView.findViewById(R.id.ivAdd);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductSize = itemView.findViewById(R.id.tvProductSize);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCartItemCount = itemView.findViewById(R.id.tvCartItemCount);
        }
    }

    public boolean isOnline() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}