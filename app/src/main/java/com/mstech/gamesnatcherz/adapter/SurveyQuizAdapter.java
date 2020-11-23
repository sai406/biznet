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
import com.mstech.gamesnatcherz.activities.SurveyQuestions;
import com.mstech.gamesnatcherz.model.Surveylistmodel;

import java.util.List;


public class SurveyQuizAdapter extends RecyclerView.Adapter<SurveyQuizAdapter.MyViewHolder> {

    private Context mContext;
    private List<Surveylistmodel> arrayListTxtQuiz = null;

    //    private List<Surveylistmodel> mylist;
    String type;

    public SurveyQuizAdapter(Context ctx, List<Surveylistmodel> arrayListTxtQuiz) {
        this.arrayListTxtQuiz = arrayListTxtQuiz;
        this.mContext = ctx;
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

        myViewHolder.tvTextQuizName.setText(arrayListTxtQuiz.get(i).getSurveyName());
        if (!arrayListTxtQuiz.get(i).getSurveyimage().equals("")) {
            Glide.with(mContext)
                    .applyDefaultRequestOptions(
                            new RequestOptions()
                                    .placeholder(R.drawable.loading)
                                    .error(R.drawable.error)
                    )
                    .load(arrayListTxtQuiz.get(i).getSurveyimage())
                    .into(myViewHolder.ivUser);

        }
        if (!arrayListTxtQuiz.get(i).getClientId().equals("")) {
            Glide.with(mContext)
                    .applyDefaultRequestOptions(
                            new RequestOptions()
                                    .placeholder(R.drawable.loading)
                                    .error(R.drawable.error)
                    )
                    .load(arrayListTxtQuiz.get(i).getClientId())
                    .into(myViewHolder.businesslogo);
        }

        if (arrayListTxtQuiz.get(i).getIsFinished().equals("1")){
            myViewHolder.tvAddress.setText("You have already played this game : "+arrayListTxtQuiz.get(i).getEndDatestring());
        }
        myViewHolder.tvDate.setText(arrayListTxtQuiz.get(i).getBusinessId());
        myViewHolder.cvTextQuiz.setOnClickListener(v -> {
            if (!arrayListTxtQuiz.get(i).getIsFinished().equals("1")) {
                Intent intent = new Intent(mContext, SurveyQuestions.class);
                intent.putExtra("QuizID", arrayListTxtQuiz.get(i).getSurveyId());
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
        ImageView ivUser , businesslogo;
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
