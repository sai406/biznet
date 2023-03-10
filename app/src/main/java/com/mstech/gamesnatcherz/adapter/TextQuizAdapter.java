package com.mstech.gamesnatcherz.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.TextQuizQuestions;
import com.mstech.gamesnatcherz.model.TextQuizQestionListModel;

import java.util.ArrayList;

/** HARISH GADDAM */

public class TextQuizAdapter extends RecyclerView.Adapter<TextQuizAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<TextQuizQestionListModel> arrayListTxtQuiz = null;

//    private List<Surveylistmodel> mylist;
    String type;

    public TextQuizAdapter(Context ctx, ArrayList<TextQuizQestionListModel> arrayListTxtQuiz) {
        this.arrayListTxtQuiz = arrayListTxtQuiz;
        this.mContext = ctx;
//        this.type = type;
    }

/*    public TextQuizAdapter(Context ctx, List<Surveylistmodel> mssglist, String type) {
        this.mylist = mssglist;
        this.mContext = ctx;
        this.type = type;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_events, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvTextQuizName.setText(arrayListTxtQuiz.get(i).getStrQuizName());

        if (!arrayListTxtQuiz.get(i).getStrQuizImagePath().equals("")) {
            Glide.with(mContext)
                    .applyDefaultRequestOptions(
                            new RequestOptions()
                                    .placeholder(R.drawable.loading)
                                    .error(R.drawable.error)
                    )
                    .load(arrayListTxtQuiz.get(i).getStrQuizImagePath())
                    .into(myViewHolder.ivUser);

        }
        if (!arrayListTxtQuiz.get(i).getStrQuizQuestionId().equals("")) {
            Glide.with(mContext)
                    .applyDefaultRequestOptions(
                            new RequestOptions()
                                    .placeholder(R.drawable.loading)
                                    .error(R.drawable.error)
                    )
                    .load(arrayListTxtQuiz.get(i).getStrQuizQuestionId())
                    .into(myViewHolder.businesslogo);
        }

        if (arrayListTxtQuiz.get(i).getStrIsFinished().equals("1")){
            myViewHolder.tvAddress.setText("You have already played this game : "+arrayListTxtQuiz.get(i).getIsEndTime());
        }
          myViewHolder.tvDate.setText(arrayListTxtQuiz.get(i).getStrQuizAnswerId());
        myViewHolder.cvTextQuiz.setOnClickListener(v -> {
            if (!arrayListTxtQuiz.get(i).getStrIsFinished().equals("1")) {
                Intent intent = new Intent(mContext, TextQuizQuestions.class);
                intent.putExtra("QuizID", arrayListTxtQuiz.get(i).getStrQuizId());
                mContext.startActivity(intent);
            }else {
                Toast.makeText(mContext,"You already played this game",Toast.LENGTH_SHORT).show();
            }

            /*if (type.equals("survey")) {

                Log.d("-->", "survey");
                Intent intent = new Intent(mContext, SurveyQuestions.class);
                intent.putExtra("serid", arrayListTxtQuiz.get(i).getStrQuizId());
                mContext.startActivity(intent);
            } else {
                Log.d("-->", "no survey");
                Intent intent = new Intent(mContext, TextQuizQuestions.class);
                intent.putExtra("serid", arrayListTxtQuiz.get(i).getStrQuizId());
                mContext.startActivity(intent);
            }*/
        });
    }

    @Override
    public int getItemCount() {
        return (arrayListTxtQuiz == null ? 0 : arrayListTxtQuiz.size());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cvTextQuiz;
        ImageView ivUser,businesslogo;
        TextView tvDate;
        TextView tvTextQuizName;
        TextView tvAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cvTextQuiz = itemView.findViewById(R.id.cvEvents);
            ivUser = itemView.findViewById(R.id.ivuser);
            businesslogo = itemView.findViewById(R.id.businesslogo);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvTextQuizName = itemView.findViewById(R.id.tvTitle);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }
}
