package com.mstech.gamesnatcherz.activities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blankj.utilcode.util.SPStaticUtils;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.adapter.TextQuizAdapter;
import com.mstech.gamesnatcherz.model.TextQuizQestionListModel;

import org.json.JSONException;

import java.util.ArrayList;

/** HARISH GADDAM */

public class TextQuiz extends AppCompatActivity {

    public static final String strTAG = TextQuiz.class.getCanonicalName();
    private Context mContext = TextQuiz.this;
    private RecyclerView rvTextQuiz = null;
    private TextQuizAdapter mAdapter;
    ProgressDialog pDialog;
    LinearLayoutManager mLayoutManager;

    TextQuizQestionListModel txtQuizModel = null;
    private ArrayList<TextQuizQestionListModel> arrayListTxtQuiz = new ArrayList<TextQuizQestionListModel>();

    Dialog dialog = null;
    String businessid ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_quiz);
        rvTextQuiz = findViewById(R.id.rvTextQuiz);
        try{
            businessid = getIntent().getExtras().getString("data");
        }catch(Exception e){
            e.printStackTrace();
        }
        setActionBarTitle();

    }

    private void setActionBarTitle() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Text Quiz");
    }
@Override
protected void onResume() {
    super.onResume();
    getTxtQuizDataFromServer();
}


    public void getTxtQuizDataFromServer() {
        arrayListTxtQuiz.clear();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="http://gamesnatcherz.com/api/GetQuizList?adminid=1&bid="+businessid+"&pgsize=-1&pgindex=1&str=&sortby=1&cid="+SPStaticUtils.getString("customerid");
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                response -> {
                    Log.e(strTAG, "onResponse-->" + url+"\n"+response.toString());

                    arrayListTxtQuiz = new ArrayList<>();
                    //if server value is null = empty
                    if (response.length() > 0) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                txtQuizModel = new TextQuizQestionListModel(
                                        response.getJSONObject(i).optString("QuizId", ""),
                                        response.getJSONObject(i).optString("QuizName", ""),
                                        response.getJSONObject(i).optString("QuizQuestionId", ""),
                                        response.getJSONObject(i).optString("QuizAnswerId", ""),
                                        response.getJSONObject(i).optString("QuizCode", ""),
                                        response.getJSONObject(i).optString("StartDatestring", ""),
                                        response.getJSONObject(i).optString("EndDatestring", ""),
                                        response.getJSONObject(i).optString("QuizImagepath", ""),
                                        response.getJSONObject(i).optString("IsFinished", ""),
                                        response.getJSONObject(i).optString("EndTimestring", "")
                                );
                                arrayListTxtQuiz.add(txtQuizModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Log.e(strTAG, "txtQList-->" + arrayListTxtQuiz.size());

                        rvTextQuiz.setHasFixedSize(true);
                        rvTextQuiz.setNestedScrollingEnabled(false);
                        rvTextQuiz.setLayoutManager(new LinearLayoutManager(mContext));
                        mAdapter = new TextQuizAdapter(mContext, arrayListTxtQuiz);
                        rvTextQuiz.setAdapter(mAdapter);
                    } else {
                        dialog = new Dialog(mContext);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setContentView(R.layout.alert1);
                        dialog.setCancelable(false);
                        TextView alertmessage=(TextView)dialog.findViewById(R.id.resultalerttvid);
                        alertmessage.setText("No Text Quiz");
                        Button okbtn = (Button) dialog.findViewById(R.id.okbtnid);
                        okbtn.setText("OK");
                        Button cancel = (Button) dialog.findViewById(R.id.cancelbtn);
                        cancel.setVisibility(View.GONE);
                        okbtn.setOnClickListener(view -> {
                            dialog.dismiss();
                            onBackPressed();
                        });
                        dialog.show();
                    }

                },
                error -> {
                    Log.e(strTAG, "onErrorResponse: " + error.getLocalizedMessage());
                });
        requestQueue.add(movieReq);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}

