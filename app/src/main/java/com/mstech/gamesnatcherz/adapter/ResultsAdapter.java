package com.mstech.gamesnatcherz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.ResultsModel;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {
private Context mContext;
private List<ResultsModel> mylist;




public ResultsAdapter(Context context, List<ResultsModel> mylist) {
        this.mylist = mylist;
        this.mContext = context;
        }

@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View itemView = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.resultlist_item, viewGroup, false);

        return new MyViewHolder(itemView);


        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.name.setText(mylist.get(i).getCustomerName());
        myViewHolder.answered.setText(mylist.get(i).getAnsweredCount());
//        myViewHolder.sno.setText(mylist.get(i).getSno());
        myViewHolder.rank.setText(String.valueOf(mylist.get(i).getRank()));
        myViewHolder.duartion.setText(mylist.get(i).getDurationString());




        }
@Override
public int getItemCount() {

        return mylist.size();
        }

public static class MyViewHolder extends RecyclerView.ViewHolder {

    TextView name , sno,answered,duartion,rank;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        name=(TextView) itemView.findViewById(R.id.name);
//        sno=(TextView) itemView.findViewById(R.id.sno);
        answered=(TextView) itemView.findViewById(R.id.answered);
        duartion=(TextView) itemView.findViewById(R.id.duration);
        rank=(TextView) itemView.findViewById(R.id.rank);
    }
}



}
