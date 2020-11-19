package com.mstech.gamesnatcherz.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.mstech.gamesnatcherz.activities.QuizQuestuions;
import com.mstech.gamesnatcherz.activities.SmartQuiz;
import com.mstech.gamesnatcherz.model.Quizmodel;
import com.squareup.picasso.Picasso;

import java.util.List;

/** HARISH GADDAM */

public class QuizlistAdapter extends RecyclerView.Adapter<QuizlistAdapter.MyViewHolder> {
    private SmartQuiz mContext;
    private List<Quizmodel> mylist;
    private String question;
    private String quizimagepath;
    private long serid;
    private String CorrectAnswerId;
    private String questionid = "0";
    private String answerid = "0";
    private String iscorrect = "0";
    private String IsquestionAvailable;
    private String SurveyquestionId = "0";
    private String SmartQuizId = "0";
    private String SmartQuizQuestionId = "0";
    private String Correctanswer;

    String merch, mtoid, Mmsgid, opt1, opt2, opt3, opt1id, opt2id, opt3id, ans1, ans2, ans3;
    SharedPreferences sharedPreferences;

    public QuizlistAdapter(SmartQuiz ctx, List<Quizmodel> mssglist) {
        this.mylist = mssglist;
        this.mContext = ctx;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_events, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {

        myViewHolder.tvSmartQuizName.setText(mylist.get(i).getSmartQuizName());
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        if (!mylist.get(i).getSmartQuizImagepath().equals("")) {
            Picasso.get().load(mylist.get(i).getSmartQuizImagepath()).into(myViewHolder.ivUser);
        }
        if (mylist.get(i).getIsFinished().equals("1")){
            myViewHolder.tvAddress.setText("You Already Played this Game "+mylist.get(i).getEndDatestring());
        }
        myViewHolder.tvTitle.setText(mylist.get(i).getAnswer());

        /** HARISH GADDAM */
        myViewHolder.cvSmartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mylist.get(i).getIsFinished().equals("1")) {
                    serid = mylist.get(i).getSmartQuizID();
                    quizimagepath = mylist.get(i).getSmartQuizImagepath();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("serid", String.valueOf(serid));
                    editor.putString("quizimage", quizimagepath);
                    editor.apply();
                    Intent intent = new Intent(mContext, QuizQuestuions.class);
                    mContext.startActivity(intent);
                }else {
                    Toast.makeText(mContext,"You already played this game",Toast.LENGTH_SHORT).show();
                }

//                Offerdata();
            }
        });


    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cvSmartQuiz;
        ImageView ivUser;
        TextView tvSmartQuizName;
        TextView tvTitle;
        TextView tvAddress;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cvSmartQuiz =  itemView.findViewById(R.id.cvEvents);
            ivUser = itemView.findViewById(R.id.ivuser);
            tvSmartQuizName = itemView.findViewById(R.id.tvTitle);
            tvTitle = itemView.findViewById(R.id.tvDate);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }
    }



}

