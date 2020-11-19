package com.mstech.gamesnatcherz.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.blankj.utilcode.util.SPStaticUtils;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.adapter.SurveyQuizAdapter;
import com.mstech.gamesnatcherz.model.Surveylistmodel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Survey extends AppCompatActivity {
    private RecyclerView gv=null;
    private SurveyQuizAdapter adapter;
    private List<Surveylistmodel> EventList = new ArrayList<Surveylistmodel>();
    ProgressDialog pDialog;
    LinearLayoutManager mLayoutManager;
    String businessid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        setActionBarTitle();
//        try{
//            businessid = getIntent().getExtras().getString("data");
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        gv= findViewById(R.id.Surveysrecyclerviewid);
        adapter=new SurveyQuizAdapter(this, EventList);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gv.setLayoutManager(mLayoutManager);
        gv.setAdapter(adapter);


    } private void setActionBarTitle() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Surveys");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Offerdata();
    }

    public void Offerdata(){
        EventList.clear();
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Surveys...\nPlease Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="http://gamesnatcherz.com/api/GetSurveyList?bid=0&cid="+ SPStaticUtils.getString("customerid")+"&pgsize=-1&pgindex=1&str=&sortby=1";
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("c", response.toString());

                        hidePDialog();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                Surveylistmodel Event=new Surveylistmodel();
                                Event.setSurveyId(obj.getString("SurveyId"));
                                Event.setSurveyimage(obj.getString("BusinessLogoPath"));
                                Event.setSurveyName(obj.getString("SurveyName"));
                                Event.setIsFinished(obj.getString("IsFinished"));
                                Event.setEndDatestring(obj.getString("EndTimestring"));
                                Event.setBusinessId(obj.getString("BusinessName"));
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

    }
}

