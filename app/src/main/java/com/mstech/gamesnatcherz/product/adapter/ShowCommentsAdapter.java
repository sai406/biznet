package com.mstech.gamesnatcherz.product.adapter;/*
package com.solutionsempowerment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.solutionsempowerment.R;

import java.util.ArrayList;

*/
/** HARISH GADDAM *//*


public class ShowCommentsAdapter extends RecyclerView.Adapter<ShowCommentsAdapter.MyViewHolder> {

    Context mContext;
    private ArrayList<ResponseGETVideoComments> arrayList;

    public ShowCommentsAdapter(Context mContext, ArrayList<ResponseGETVideoComments> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ShowCommentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_show_comments, viewGroup, false);
        mContext = view.getContext();

        return new ShowCommentsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowCommentsAdapter.MyViewHolder holder, final int position) {

//        holder.tvUserName.setText((arrayList.get(position).getComment().isEmpty()) ? "" : arrayList.get(position).getName());
        holder.tvDate.setText((arrayList.get(position).getCreatedDateDisplay().isEmpty()) ? "" : arrayList.get(position).getCreatedDateDisplay());
        holder.tvUserName.setText((arrayList.get(position).getComment().isEmpty()) ? "" : arrayList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvShowComments;
        TextView tvUserName;
        TextView tvDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tvUserName);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvShowComments = itemView.findViewById(R.id.tvShowComments);
        }
    }
}
*/
