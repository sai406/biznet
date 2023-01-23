package com.mstech.gamesnatcherz.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blankj.utilcode.util.SPStaticUtils;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.adapter.QuizlistAdapter;
import com.mstech.gamesnatcherz.model.Quizmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/** HARISH GADDAM */

public class SmartQuiz extends AppCompatActivity {

    /** HARISH GADDAM */
    public static final String TAG = SmartQuiz.class.getSimpleName();
    private RecyclerView gv=null;
    private QuizlistAdapter adapter;
    private List<Quizmodel> EventList = new ArrayList<Quizmodel>();
    ProgressDialog pDialog;
    LinearLayoutManager mLayoutManager;
    String businessid ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_quiz);
        setActionBarTitle();
        try{
            businessid = getIntent().getExtras().getString("data");
        }catch(Exception e){
            e.printStackTrace();
        }
        gv= findViewById(R.id.Surveysrecyclerviewid);
        adapter=new QuizlistAdapter(this, EventList);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gv.setLayoutManager(mLayoutManager);
        gv.setAdapter(adapter);

    }

    private void setActionBarTitle() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Smart Quiz");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Offerdata();
    }

    public void Offerdata() {
        EventList.clear();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Quiz...\nPlease Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="https://apmmarketing.co.nz/api/GetSmartQuizList?pgsize=-1&pgindex=1&str=&sortby=1&bid="+businessid+"&cid="+ SPStaticUtils.getString("customerid");
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        /** HARISH GADDAM */
                        VolleyLog.wtf(TAG + "list: " + response.toString());
                        hidePDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Quizmodel Event=new Quizmodel();
                                Event.setSmartQuizID(obj.getLong("SmartQuizId"));
                                Event.setSmartQuizImagepath(obj.getString("SmartQuizImagepath"));
                                Event.setSmartQuizurlpath(obj.getString("BusinessLogoPath"));
                                Event.setSmartQuizName(obj.getString("SmartQuizName"));
                                Event.setEndDatestring(obj.getString("EndTimestring"));
                                Event.setIsFinished(obj.getString("IsFinished"));
                                Event.setAnswer(obj.getString("BusinessName"));
                                EventList.add(Event);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(movieReq);
    }
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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

    }}
