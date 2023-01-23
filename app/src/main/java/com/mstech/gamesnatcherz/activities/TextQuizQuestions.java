package com.mstech.gamesnatcherz.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blankj.utilcode.util.SPStaticUtils;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.adapter.ResultsAdapter;
import com.mstech.gamesnatcherz.model.ResultsModel;
import com.mstech.gamesnatcherz.model.SurveyQuestionModel;
import com.mstech.gamesnatcherz.utils.DatabaseHelper;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TextQuizQuestions extends AppCompatActivity {
    private TextView question,q,count,gamestatus;
    private RecyclerView gv=null;
    private RadioGroup options;
    private ImageView next,surveyimage;
    private ProgressDialog pDialog;
    public int counter , pos;
    DatabaseHelper databaseHelper;
    LinearLayoutManager mLayoutManager;
    private ResultsAdapter adapter;
    private List<ResultsModel> ResultList = new ArrayList<ResultsModel>();
    RadioButton rb;
    final ArrayList<String> answerary = new ArrayList<String>();
    final ArrayList<String> answeridary = new ArrayList<String>();
    private String custid,serid,questionid="0",answerid="0",answerno="0",iscorrect="0",IsquestionAvailable,SurveyquestionId="0",CorrectAnswerId;
    private SharedPreferences sharedPreferences;
    int i ,  index =0;
    ScrollView quizlayout;
    LinearLayout timerlayout,resultslayout;
    RadioButton rb1,rb2,rb3,rb4;
    RecyclerView recyclerview;
    Boolean ischeck = false;
    ImageView prizeimage;
    TextView prizetxt;
    private final List<SurveyQuestionModel> EventList = new ArrayList<SurveyQuestionModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_quiz_questions);
        prizeimage=(ImageView) findViewById(R.id.prizeimage) ;
        prizetxt=(TextView) findViewById(R.id.prizetxt) ;
        question=(TextView)findViewById(R.id.surveyquestiontvid) ;
        count=(TextView)findViewById(R.id.count) ;
        gamestatus=(TextView)findViewById(R.id.gamestatus) ;
        next=(ImageView)findViewById(R.id.surveynextquestionid) ;
        options=(RadioGroup) findViewById(R.id.radiogroup) ;
        timerlayout=(LinearLayout) findViewById(R.id.timerlayout) ;
        resultslayout=(LinearLayout) findViewById(R.id.resultslayout) ;
        recyclerview=(RecyclerView) findViewById(R.id.recyclerview) ;
        quizlayout=(ScrollView) findViewById(R.id.quizlayout) ;
        databaseHelper = new DatabaseHelper(this);
        options.setVisibility(View.GONE);
        q=(TextView)findViewById(R.id.qtvid);
        next.setVisibility(View.GONE);
        adapter=new ResultsAdapter(this, ResultList);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setAdapter(adapter);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        custid= SPStaticUtils.getString("customerid");
        serid=getIntent().getStringExtra("QuizID");
        databaseHelper.removeAll();
        options.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ischeck= true;
                View radioButton = radioGroup.findViewById(i);
                index = radioGroup.indexOfChild(radioButton);
                answerid=EventList.get(index).getSurveyanswerId();
                answerno=EventList.get(index).getAnswerNumber();
                next.setVisibility(View.VISIBLE);
            }
        });
        setActionBarTitle();
        Offerdata();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                  EventList.clear();
                  EventList.removeAll(EventList);
                  options.removeAllViews();
                  pos= Integer.parseInt(databaseHelper.getTextquizdata(serid).getTposition());
                  databaseHelper.updateTextPosition(String.valueOf(pos+1),serid);
                  try {
                      JSONObject ansobj = new JSONObject();
                      ansobj.put("AnswerId", answerid);
                      ansobj.put("AnswerNumber",answerno);
                      if (CorrectAnswerId.equals(String.valueOf(index+1))){
                          ansobj.put("IsCorrect","1");
                      }else {
                          ansobj.put("IsCorrect","0");
                      }

                      try {
                          JSONArray questionarray = new JSONArray(databaseHelper.getTextquizdata(serid).getTquestions());
                          JSONObject obj = questionarray.getJSONObject(pos);
                          SurveyquestionId = questionarray.getJSONObject(pos).getString("QuizQuestionId");
                          String quesnum = questionarray.getJSONObject(pos).getString("QuestionNum");
                          JSONArray optionsarray = obj.getJSONArray("answers");
                          ansobj.put("QuestionId",SurveyquestionId);
                          ansobj.put("QuestionNumber",quesnum);
                      } catch (JSONException e) {
                          e.printStackTrace();
                      }
                      if (ischeck) {
                          JSONArray ansarray = null;
                          if (databaseHelper.getTextquizdata(serid).getTanswer().contains("[")) {
                              ansarray = new JSONArray(databaseHelper.getTextquizdata(serid).getTanswer());

                          } else {
                              ansarray = new JSONArray();
                          }
                          ansarray.put(ansobj);
                          databaseHelper.updateTextanswer(ansarray.toString(), serid);
                          Log.d("TAG", "onClick: " + databaseHelper.getTextquizdata(serid).getTanswer());
                      }
                  } catch (JSONException e) {
                      e.printStackTrace();
                  }
                  datarefresh(serid);

            }
        });
    }
    private void setActionBarTitle() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quiz");
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

    public void Offerdata(){
        EventList.clear();
        options.setVisibility(View.GONE);
        next.setVisibility(View.GONE);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading Survey Questions...\nPlease Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="https://apmmarketing.co.nz/api/GetQuizStatusByCustomer?qzid="+serid+"&cid="+custid;
        Log.d("c", url);

        StringRequest movieReq = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("c", response);
                        hidePDialog();
                        try {
                            JSONObject obj = new JSONObject(response);
                            int IsFinished=obj.getInt("IsFinished");
                            int gameStatus=obj.getInt("GameStatus");
                            final String gametext=obj.getString("StatusMessage");
//                            if (gameStatus==1){
//                                quizlayout.setVisibility(View.GONE);
//                                timerlayout.setVisibility(View.VISIBLE);
//                                resultslayout.setVisibility(View.GONE);
//                                int duration=obj.getInt("Duration");
//                                startTimer(duration*1000,gametext);
//                            }else

//                                if(IsFinished!=0) {
                                quizlayout.setVisibility(View.VISIBLE);
                                timerlayout.setVisibility(View.GONE);
                                resultslayout.setVisibility(View.GONE);
                                EventList.clear(); try {
                                    JSONObject object = new JSONObject(response);
                                    JSONArray questionarray = object.getJSONArray("Questions");
                                    if (databaseHelper.getTextquizdata(serid) == null) {
//                                        Toast.makeText(TextQuizQuestions.this, "entered", Toast.LENGTH_SHORT).show();
                                        databaseHelper.insertTextQuestion(serid,questionarray.toString(),"0","0", String.valueOf(System.currentTimeMillis()));
                                    }
                                    datarefresh(serid);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


//                            }else {
//                                quizlayout.setVisibility(View.GONE);
//                                timerlayout.setVisibility(View.GONE);
//                                resultslayout.setVisibility(View.VISIBLE);
//                                JSONArray resultsdata = obj.getJSONArray("Results");
//                                gamestatus.setText(obj.getString("StatusMessage"));
//                                for (int i = 0; i < resultsdata.length(); i++) {
//                                    try {
//                                        JSONObject objj = resultsdata.getJSONObject(i);
//                                        ResultsModel result=new ResultsModel();
//                                        result.setCustomerName(objj.getString("CustomerName"));
//                                        result.setAnsweredCount(objj.getString("CorrectAnswerCount")+"/"+objj.getString("AnsweredCount"));
//                                        result.setDurationString(objj.getString("DurationString"));
//                                        result.setRank(objj.getInt("Rank"));
//                                        result.setSno(String.valueOf(i+1));
//                                        ResultList.add(result);
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                    adapter.notifyDataSetChanged();
//                                }
//
//                                Toast.makeText(getApplicationContext(),"Survey Finished", Toast.LENGTH_LONG).show();
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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

    private void datarefresh(String serid) {
        q.setVisibility(View.VISIBLE);
        options.setVisibility(View.VISIBLE);
        ischeck=false;
        pos= Integer.parseInt(databaseHelper.getTextquizdata(serid).getTposition());
        try {
            JSONArray questionarray = new JSONArray(databaseHelper.getTextquizdata(serid).getTquestions());
            if (pos>=questionarray.length()){
                try {
                    JSONArray array = new JSONArray(databaseHelper.getTextquizdata(serid).getTanswer());
                    JSONObject result = new JSONObject();
                    result.put("QuizId",serid);
                    result.put("CustomerId",custid);
                    result.put("Duration", System.currentTimeMillis()- Long.parseLong(databaseHelper.getTextquizdata(serid).getTduration()));
                    int count =0;
                    for (int i=0 ;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);
                        if (obj.getString("IsCorrect").equals("1")){
                            count = count+1;
                        }
                    }
                    result.put("CorrectAnsweredCount",count);
                    result.put("AnsweredCount",array.length());
                    result.put("Answers",array);
                    Log.d("TAG", "onClick: " +result);
                    SubmitAnswers(result.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {
                JSONObject obj = questionarray.getJSONObject(pos);
                CorrectAnswerId = questionarray.getJSONObject(pos).getString("CorrectAnswerId");
                serid=sharedPreferences.getString("serid","");
                SurveyquestionId = obj.getString("QuizQuestionId");
                String SurveyId = obj.getString("QuizId");
                String Question = obj.getString("Question");
                question.setText(Question);
                String QuestionNum = obj.getString("QuestionNum");
//                iscorrect = obj.getString("Correctanswer");
                String answers = obj.getString("answers");
                JSONArray arry=new JSONArray(answers);
                for ( i = 0; i < arry.length(); i++) {
                    try {
                        JSONObject array = arry.getJSONObject(i);
                        SurveyQuestionModel Event = new SurveyQuestionModel();
                        Event.setAnswer(array.getString("Answer"));
                        Event.setSurveyanswerId(array.getString("QuizAnswerId"));
                        Event.setAnswerNumber(array.getString("AnswerNumber"));
                        EventList.add(Event);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    rb = new RadioButton(getApplicationContext());
                    rb.setTextSize(18);
                    rb.setText(EventList.get(i).getAnswer());
                    rb.setChecked(false);
                    options.addView(rb);
                }
            }

//             pos = Integer.parseInt(databaseHelper.getImageData(s).getAnsweredcount());

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    private void startTimer(int noOfMinutes, final String gametext) {
        CountDownTimer countDownTimer = new CountDownTimer(noOfMinutes, 1000) {
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                //Convert milliseconds into hour,minute and seconds
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                count.setText(gametext+"\n"+hms);//set text
            }
            public void onFinish() {
                count.setText("TIME'S UP!!");
//                Offerdata();
                //On finish change timer text
            }
        }.start();

    }
    public void SubmitAnswers(String jsonBody){
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(TextQuizQuestions.this);
        String url="https://apmmarketing.co.nz/api/InsertQuizCustomerAllAnswers";
        final String requestBody = jsonBody;
        Log.d("offers","response"+requestBody);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response","response"+response);
                pDialog.dismiss();
                try {
                 JSONObject obj = new JSONObject(response);
                    quizlayout.setVisibility(View.GONE);
                    timerlayout.setVisibility(View.GONE);
                    resultslayout.setVisibility(View.VISIBLE);
//                    gamestatus.setText(obj.getString("StatusMessage"));
                    JSONArray resultsdata = obj.getJSONArray("Results");
                    JSONObject prizedata = obj.getJSONObject("PrizeDetails");
                    if (prizedata.getString("PrizePath").equals("")){
                        Picasso.get().load(R.drawable.betterluck).placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(prizeimage);
                    }else {
                        Picasso.get().load(prizedata.getString("PrizePath")).placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(prizeimage);
                    }
                   //                    Picasso.get().load("http://gamesnatcherz.com/ApplicationFiles/smartquizgameprizes/2/one-cheese-pizza-2741457.jpg").placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(prizeimage);
 prizetxt.setText(prizedata.getString("PrizeMessage")+"\n"+"  you can check this prize under claimed prizes ");
//                    prizetxt.setText("Won Free Pizza Coupon."+"\n you can see this Prize in your ClaimedPrizes Section");
//                    gamestatus.setText((obj.getString("StatusMessage")));

                    for (int i = 0; i < resultsdata.length(); i++) {
                        try {
                            JSONObject objj = resultsdata.getJSONObject(i);
                            ResultsModel result=new ResultsModel();
                            result.setCustomerName(objj.getString("CustomerName"));
                            result.setAnsweredCount(objj.getString("CorrectAnswerCount")+"/"+objj.getString("AnsweredCount"));
                            result.setDurationString(objj.getString("DurationString"));
                            result.setRank(objj.getInt("Rank"));
                            result.setSno(String.valueOf(i+1));
                            ResultList.add(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }}
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                    adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                pDialog.dismiss();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return requestBody == null ? null : requestBody.getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }


}
