package com.mstech.gamesnatcherz.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.products.ResponseProductReviews;

import java.util.ArrayList;

/**
 * HARISH GADDAM
 */

public class RatingReviewsAdapter extends RecyclerView.Adapter<RatingReviewsAdapter.ViewHolerRoster> {

    Context mContext;
    private ArrayList<ResponseProductReviews> arrayList = new ArrayList<>();

    public RatingReviewsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public RatingReviewsAdapter(Context mContext, ArrayList<ResponseProductReviews> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolerRoster onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_reviews, parent, false);
        mContext = view.getContext();

        return new ViewHolerRoster(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolerRoster holder, int position) {

        ResponseProductReviews productReviews = arrayList.get(position);
//        holder.ratingBar.setRating(!(productReviews.getReview().isEmpty()) ? arrayList.get(position).getProductName() : "");
        holder.tvName.setText(!(productReviews.getName().isEmpty()) ? productReviews.getName() : "");
        holder.tvDate.setText(!(productReviews.getCreatedDateDisplay().isEmpty()) ? productReviews.getCreatedDateDisplay() : "");
        holder.tvDescription.setText(!(productReviews.getReview().isEmpty()) ? productReviews.getReview() : "");
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class ViewHolerRoster extends RecyclerView.ViewHolder {

        private RatingBar ratingBar;
        private TextView tvName;
        private TextView tvDate;
        private TextView tvDescription;

        public ViewHolerRoster(@NonNull View itemView) {
            super(itemView);

            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvName = itemView.findViewById(R.id.tvName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);

            ratingBar.setVisibility(View.GONE);
        }
    }
}