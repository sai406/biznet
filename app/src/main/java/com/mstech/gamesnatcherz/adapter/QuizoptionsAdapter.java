package com.mstech.gamesnatcherz.adapter;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.ScratchCardLayout;
import com.mstech.gamesnatcherz.ScratchListener;
import com.mstech.gamesnatcherz.activities.QuizQuestuions;
import com.mstech.gamesnatcherz.model.SurveyQuestionModel;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class QuizoptionsAdapter extends RecyclerView.Adapter<QuizoptionsAdapter.MyViewHolder> implements ScratchListener {
    private QuizQuestuions mContext;
    private List<SurveyQuestionModel> mylist;
    String custid,merch,mtoid,Mmsgid;
    SharedPreferences sharedPreferences;

    public QuizoptionsAdapter(QuizQuestuions ctx, List<SurveyQuestionModel> mssglist) {
        this.mylist = mssglist;
        this.mContext = ctx;

    }

    @NonNull
    @Override
    public QuizoptionsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.customscratchcard, viewGroup, false);



        return new QuizoptionsAdapter.MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull QuizoptionsAdapter.MyViewHolder myViewHolder, final int i) {

        Drawable d;
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            d = drawableFromUrl(mylist.get(i).getAnswerImagePath());

        } catch (IOException e) {
            d = mContext.getResources().getDrawable(R.mipmap.ic_launcher);
            e.printStackTrace();

        }
        myViewHolder.scratchCardLayout.setScratchDrawable(d);
        myViewHolder.scratchCardLayout.setScratchWidthDip(50f);
        myViewHolder.scratchCardLayout.setScratchEnabled(true);
        myViewHolder.scratchCardLayout.resetScratch();
        myViewHolder.scratchCardLayout.setScratchListener((ScratchListener) mContext);



    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ScratchCardLayout scratchCardLayout;
        ImageView scartchimage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            scartchimage=(ImageView)itemView.findViewById(R.id.quizimageid);
            scratchCardLayout=(ScratchCardLayout) itemView.findViewById(R.id.scratchCard);



        }
    }
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }

    public void onScratchStarted(@NotNull ScratchCardLayout scratchCardLayout, int percentCompleted) {
    }

    public void onScratchProgress(ScratchCardLayout scratchCardLayout, int atLeastScratchedPercent) {
        if(scratchCardLayout.equals(mylist.get(0))){
            if(atLeastScratchedPercent>=0.1){
                scratchCardLayout.onFullReveal();
                Toast.makeText(mContext,"Reaveled", Toast.LENGTH_LONG).show();
                scratchCardLayout.setScratchEnabled(false);

            }}
        else if(scratchCardLayout.equals(mylist.get(1))){

            if(atLeastScratchedPercent>=1){
                scratchCardLayout.onFullReveal();
                Toast.makeText(mContext,"Reaveled", Toast.LENGTH_LONG).show();
                scratchCardLayout.setScratchEnabled(false);

            }}

                else if(scratchCardLayout.equals(mylist.get(2))){

            if(atLeastScratchedPercent>=1){
                scratchCardLayout.onFullReveal();
                Toast.makeText(mContext,"Reaveled", Toast.LENGTH_LONG).show();
                scratchCardLayout.setScratchEnabled(false);

            }}

    }


    public void onScratchComplete() {

    }



}

