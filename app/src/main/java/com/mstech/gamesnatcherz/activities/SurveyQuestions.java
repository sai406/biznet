package com.mstech.gamesnatcherz.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blankj.utilcode.util.SPStaticUtils;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.model.SurveyQuestionModel;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SurveyQuestions extends AppCompatActivity {
    private TextView question, q;
    private final RecyclerView gv = null;
    private RadioGroup options;
    private ImageView next, surveyimage, prizeimage;
    CardView surveyquestionimageid;
    private ProgressDialog pDialog;
    LinearLayoutManager mLayoutManager;
    RadioButton rb;
    final ArrayList<String> answerary = new ArrayList<String>();
    final ArrayList<String> answeridary = new ArrayList<String>();
    private String custid;
    private String serid;
    private final String questionid = "0";
    private String answerid = "0";
    private String iscorrect = "0";
    private String IsquestionAvailable;
    private String SurveyquestionId = "0";
    private SharedPreferences sharedPreferences;
    int i;
    RadioButton rb1, rb2, rb3, rb4;
    EditText message;

    private final List<SurveyQuestionModel> EventList = new ArrayList<SurveyQuestionModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_questions);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Survey Details");
        question = findViewById(R.id.surveyquestiontvid);
        next = findViewById(R.id.surveynextquestionid);
        surveyquestionimageid = findViewById(R.id.surveyquestionimageid);
        prizeimage = findViewById(R.id.prizeimage);
        message = findViewById(R.id.message);
        options = findViewById(R.id.radiogroup);
        options.setVisibility(View.GONE);
        q = findViewById(R.id.qtvid);
        next.setVisibility(View.GONE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        custid = SPStaticUtils.getString("customerid");
        serid = getIntent().getStringExtra("QuizID");

        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                answerid = EventList.get(index).getSurveyanswerId();
                next.setVisibility(View.VISIBLE);
            }
        });

        Offerdata();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventList.clear();
                EventList.removeAll(EventList);
                options.removeAllViews();
                Offerdata();
            }
        });
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

    public void loadQuestions() {
        https:
//apmmarketing.co.nz/api/GetSurveybyId?sid=7&cid=3

        EventList.clear();
        options.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Survey Questions...\nPlease Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://apmmarketing.co.nz/api/GetSurveybyId?sid=" + serid + "&cid=" + custid;
        Log.d("TAG", "Offerdata: " + "https://apmmarketing.co.nz/api/GetSurveybyId?sid=" + serid + "&cid=" + custid);
        StringRequest movieReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("c", response);
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            int IsFinished = obj.getInt("IsFinished");
                            JSONObject Survey = obj.getJSONObject("SurveyQuestion");
                            if (Survey.has("SurveyquestionId")) {
                                EventList.clear();
                                q.setVisibility(View.VISIBLE);
                                options.setVisibility(View.VISIBLE);
                                String SurveyQuestion = obj.getString("SurveyQuestion");
                                JSONObject SurveyQuestionobj = new JSONObject(SurveyQuestion);
                                SurveyquestionId = SurveyQuestionobj.getString("SurveyquestionId");
                                if (SurveyQuestionobj.getInt("IsActive") == 1) {
                                    message.setVisibility(View.VISIBLE);
                                    message.setText("");
                                } else {
                                    message.setVisibility(View.GONE);
                                }
                                String SurveyId = SurveyQuestionobj.getString("SurveyId");
                                String Question = SurveyQuestionobj.getString("Question");
                                question.setText(Question);
                                String QuestionNum = SurveyQuestionobj.getString("QuestionNum");
                                iscorrect = SurveyQuestionobj.getString("Correctanswer");
                                IsquestionAvailable = SurveyQuestionobj.getString("IsquestionAvailable");
                                String answers = SurveyQuestionobj.getString("answers");
                                JSONArray arry = new JSONArray(answers);
                                for (i = 0; i < arry.length(); i++) {
                                    try {
                                        JSONObject array = arry.getJSONObject(i);
                                        SurveyQuestionModel Event = new SurveyQuestionModel();
                                        Event.setAnswer(array.getString("Answer"));
                                        Event.setSurveyanswerId(array.getString("SurveyanswerId"));
                                        EventList.add(Event);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    rb = new RadioButton(getApplicationContext());
                                    rb.setTextSize(18);
                                    rb.setText(EventList.get(i).getAnswer());
                                    options.addView(rb);
                                }

                            } else {
                                options.setVisibility(View.GONE);
                                q.setVisibility(View.GONE);
                                message.setVisibility(View.GONE);
                                next.setVisibility(View.GONE);
                                prizeimage.setVisibility(View.VISIBLE);
                                JSONObject prize = Survey.getJSONObject("PrizeDetails");
                                if (!prize.getString("PrizePath").equals("")) {
                                    Picasso.get().load(prize.getString("PrizePath")).error(R.drawable.betterluck).into(prizeimage);

                                } else {
                                    prizeimage.setBackgroundDrawable(getResources().getDrawable(R.drawable.betterluck));
                                }
                                if (prize.getString("PrizeMessage") == null) {
                                    question.setText("Better Luck NextTime.");
                                } else {
                                    question.setText(prize.getString("PrizeMessage"));
                                }
                                question.setGravity(Gravity.CENTER_HORIZONTAL);
                                surveyquestionimageid.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Survey Finished", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(movieReq);
    }


    public void Offerdata() {
        EventList.clear();
        options.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Survey Questions...\nPlease Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://apmmarketing.co.nz/api/InsertCustomerSurveyAnswer?sid=" + serid + "&cid=" + custid + "&qid=" + SurveyquestionId
                + "&aid=" + answerid + "&answertext=" + message.getText().toString() + "&Duration=0";
        Log.d("TAG", "Offerdata: "+"https://apmmarketing.co.nz/api/InsertCustomerSurveyAnswer?sid=" + serid + "&cid=" + custid + "&qid=" + SurveyquestionId
                + "&aid=" + answerid + "&answertext=" + message.getText().toString() + "&Duration=0");
        StringRequest movieReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("c", response);
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            int IsFinished = obj.getInt("IsFinished");
                            JSONObject Survey = obj.getJSONObject("SurveyQuestion");
                            if (Survey.has("SurveyquestionId")) {
                                EventList.clear();
                                q.setVisibility(View.VISIBLE);
                                options.setVisibility(View.VISIBLE);
                                String SurveyQuestion = obj.getString("SurveyQuestion");
                                JSONObject SurveyQuestionobj = new JSONObject(SurveyQuestion);
                                SurveyquestionId = SurveyQuestionobj.getString("SurveyquestionId");
                                if (SurveyQuestionobj.getInt("IsActive") == 1) {
                                    message.setVisibility(View.VISIBLE);
                                    message.setText("");
                                } else {
                                    message.setVisibility(View.GONE);
                                }
                                String SurveyId = SurveyQuestionobj.getString("SurveyId");
                                String Question = SurveyQuestionobj.getString("Question");
                                question.setText(Question);
                                String QuestionNum = SurveyQuestionobj.getString("QuestionNum");
                                iscorrect = SurveyQuestionobj.getString("Correctanswer");
                                IsquestionAvailable = SurveyQuestionobj.getString("IsquestionAvailable");
                                String answers = SurveyQuestionobj.getString("answers");
                                JSONArray arry = new JSONArray(answers);
                                for (i = 0; i < arry.length(); i++) {
                                    try {
                                        JSONObject array = arry.getJSONObject(i);
                                        SurveyQuestionModel Event = new SurveyQuestionModel();
                                        Event.setAnswer(array.getString("Answer"));
                                        Event.setSurveyanswerId(array.getString("SurveyanswerId"));
                                        EventList.add(Event);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    rb = new RadioButton(getApplicationContext());
                                    rb.setTextSize(18);
                                    rb.setText(EventList.get(i).getAnswer());
                                    options.addView(rb);
                                }

                            } else {
                                options.setVisibility(View.GONE);
                                q.setVisibility(View.GONE);
                                message.setVisibility(View.GONE);
                                next.setVisibility(View.GONE);
                                prizeimage.setVisibility(View.VISIBLE);
                                JSONObject prize = Survey.getJSONObject("PrizeDetails");
                                if (!prize.getString("PrizePath").equals("")) {
                                    Picasso.get().load(prize.getString("PrizePath")).error(R.drawable.betterluck).into(prizeimage);

                                } else {
                                    prizeimage.setBackgroundDrawable(getResources().getDrawable(R.drawable.betterluck));
                                }
                                if (prize.getString("PrizeMessage") == null) {
                                    question.setText("Better Luck NextTime.");
                                } else {
                                    question.setText(prize.getString("PrizeMessage"));
                                }
                                question.setGravity(Gravity.CENTER_HORIZONTAL);
                                surveyquestionimageid.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Survey Finished", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
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
}
