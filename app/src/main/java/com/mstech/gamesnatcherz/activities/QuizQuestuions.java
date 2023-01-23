package com.mstech.gamesnatcherz.activities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.mstech.gamesnatcherz.ScratchCardLayout;
import com.mstech.gamesnatcherz.ScratchListener;
import com.mstech.gamesnatcherz.adapter.QuizoptionsAdapter;
import com.mstech.gamesnatcherz.adapter.ResultsAdapter;
import com.mstech.gamesnatcherz.model.ResultsModel;
import com.mstech.gamesnatcherz.model.SurveyQuestionModel;
import com.mstech.gamesnatcherz.utils.DatabaseHelper;
import com.mstech.gamesnatcherz.utils.GridSpacingItemDecoration;
import com.mstech.gamesnatcherz.utils.Quizinterface;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class QuizQuestuions extends AppCompatActivity implements ScratchListener, Quizinterface {
    ImageView quizimage,sciv1,sciv2,sciv3;
    ScratchCardLayout scratchCardLayout1;
    ScratchCardLayout scratchCardLayout2;
    ScratchCardLayout scratchCardLayout3;
    private ProgressDialog pDialog;
    TextView question , count,gamestatus;
    LinearLayout quiz_layout , first ,second;
    SharedPreferences sharedPreferences;
    String opt1,opt2,opt3,opt1id,opt2id,opt3id,ans1,ans2,ans3;
    QuizoptionsAdapter adapter;
    RecyclerView quizlist;
    private ResultsAdapter adapterr;
    LinearLayoutManager mLayoutManager;
    DatabaseHelper databaseHelper;
    RecyclerView recyclerview;
    LinearLayout timerlayout,resultslayout;
    private List<ResultsModel> ResultList = new ArrayList<ResultsModel>();
    private String custid,quizimagepath,serid,questions,CorrectAnswerId,questionid="0",answerid="0",iscorrect="0",IsquestionAvailable,SurveyquestionId="0",SmartQuizId="0",SmartQuizQuestionId="0",ansid="0";
    private final List<SurveyQuestionModel> EventList = new ArrayList<SurveyQuestionModel>();
    Drawable d,f,g;
    int i =0;
    Button nxt_btn,play;
    public int pos , correctanswercount;
    ImageView prizeimage,gameimage;
    TextView prizetxt,gamedescription;
    String drawabledata ="";
    List<List> listt = new ArrayList();
    List<List<Drawable>> list = new ArrayList<>();
    LinearLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questuions);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }
        getSupportActionBar().setTitle("SmartQuiz");
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);
        question=(TextView)findViewById(R.id.questiontvid) ;
        gamedescription=(TextView)findViewById(R.id.gamedescription) ;
        progress=(LinearLayout)findViewById(R.id.progress) ;
        count=(TextView)findViewById(R.id.count) ;
        prizeimage=(ImageView) findViewById(R.id.prizeimage) ;
        gameimage=(ImageView) findViewById(R.id.gameimage) ;
        prizetxt=(TextView) findViewById(R.id.prizetxt) ;
        gamestatus=(TextView)findViewById(R.id.gamestatus) ;
        databaseHelper = new DatabaseHelper(this);
        nxt_btn=(Button) findViewById(R.id.nxt_btn) ;
        play=(Button) findViewById(R.id.play) ;
        quizimage=(ImageView) findViewById(R.id.quizimageid) ;
        sciv1=(ImageView) findViewById(R.id.quizimage1id) ;
        sciv2=(ImageView) findViewById(R.id.quizimage2id) ;
        sciv3=(ImageView) findViewById(R.id.quizimage3id) ;
        first=(LinearLayout) findViewById(R.id.first) ;
        second=(LinearLayout) findViewById(R.id.second) ;
        timerlayout=(LinearLayout) findViewById(R.id.timerlayout) ;
        resultslayout=(LinearLayout) findViewById(R.id.resultslayout) ;
        recyclerview=(RecyclerView) findViewById(R.id.recyclerview) ;
        quiz_layout=(LinearLayout) findViewById(R.id.quiz_layout) ;
        quizlist=(RecyclerView) findViewById(R.id.quizoptionsrecyclerid) ;
        scratchCardLayout1 = findViewById(R.id.scratchCard1);
        scratchCardLayout2 = findViewById(R.id.scratchCard2);
        scratchCardLayout3 = findViewById(R.id.scratchCard3);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        custid= SPStaticUtils.getString("customerid");
        serid=sharedPreferences.getString("serid","");
        quizimagepath=sharedPreferences.getString("quizimage","");
        databaseHelper.removeAll();
        Offerdata();
        quizimagepath=sharedPreferences.getString("quizimage","");
        SmartQuizQuestionId=sharedPreferences.getString("SmartQuizQuestionId","");

        if (!quizimagepath.equals("")) {
            Picasso.get().load(quizimagepath).placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(quizimage);

        } if (!quizimagepath.equals("")) {
            Picasso.get().load(quizimagepath).placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(gameimage);

        }
        adapter=new QuizoptionsAdapter(this, EventList);
        adapterr=new ResultsAdapter(this, ResultList);
        mLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setAdapter(adapterr);
        quizlist.setLayoutManager(new GridLayoutManager(this,3));
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(this, R.dimen.columnspace);
        quizlist.addItemDecoration(itemDecoration);
        quizlist.setAdapter(adapter);
        first.setVisibility(View.GONE);
        play.setVisibility(View.GONE);
        nxt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos= Integer.parseInt(databaseHelper.getImagequizdata(serid).getImposition());
                databaseHelper.updateImagePosition(String.valueOf(pos+1),serid);
                try {
                    JSONObject ansobj = new JSONObject();
                    ansobj.put("AnswerId",answerid);
                    ansobj.put("AnswerNumber",ansid);
                    ansobj.put("IsCorrect",iscorrect);
                    try {
                        JSONArray questionarray = new JSONArray(databaseHelper.getImagequizdata(serid).getImquestions());
                        JSONObject obj = questionarray.getJSONObject(pos);
                        SmartQuizQuestionId = questionarray.getJSONObject(pos).getString("SmartQuizQuestionId");
                        String quesnum = questionarray.getJSONObject(pos).getString("QuestionNum");
                        JSONArray optionsarray = obj.getJSONArray("answers");
                        ansobj.put("QuestionId",SmartQuizQuestionId);
                        ansobj.put("QuestionNumber",quesnum);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    JSONArray ansarray= null;
                    if (databaseHelper.getImagequizdata(serid).getImanswer().contains("[")){
                        ansarray= new JSONArray(databaseHelper.getImagequizdata(serid).getImanswer());

                    }else {
                        ansarray = new JSONArray();
                    }
                    ansarray.put(ansobj);
                    databaseHelper.updateImageanswer(ansarray.toString(),serid);
                    Log.d("TAG", "onClick: "+ databaseHelper.getImagequizdata(serid).getImanswer());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                datarefresh(serid);

            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                first.setVisibility(View.VISIBLE);
                second.setVisibility(View.GONE);
            }
        });


    }
    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(Resources.getSystem(), x);
    }

    @Override
    public void onScratchStarted(ScratchCardLayout scratchCardLayout, int percentCompleted) {
        if(scratchCardLayout.equals(scratchCardLayout1)){
            scratchCardLayout3.setScratchEnabled(false);
            scratchCardLayout2.setScratchEnabled(false);
            scratchCardLayout1.setScratchEnabled(true);
        }else if (scratchCardLayout.equals(scratchCardLayout2)){
            scratchCardLayout3.setScratchEnabled(false);
            scratchCardLayout2.setScratchEnabled(true);
            scratchCardLayout1.setScratchEnabled(false);
        }else if (scratchCardLayout.equals(scratchCardLayout3)){
            scratchCardLayout3.setScratchEnabled(true);
            scratchCardLayout2.setScratchEnabled(false);
            scratchCardLayout1.setScratchEnabled(false);
        }

    }

    @Override
    public void onScratchProgress(ScratchCardLayout scratchCardLayout, int atLeastScratchedPercent) {
        if(scratchCardLayout.equals(scratchCardLayout1)){
            if(atLeastScratchedPercent>=50){
                scratchCardLayout.onFullReveal();
//                Toast.makeText(getApplicationContext(),"Reaveled",Toast.LENGTH_LONG).show();
                scratchCardLayout3.setScratchEnabled(false);
                scratchCardLayout2.setScratchEnabled(false);
                scratchCardLayout1.setScratchEnabled(false);
                if(CorrectAnswerId.equals(ans1)){
                    iscorrect="1";
                }else {
                    iscorrect="0";
                }
                answerid=opt1id;
                ansid = ans1;
                nxt_btn.setEnabled(true);

            }
        }else if(scratchCardLayout.equals(scratchCardLayout2)){
            if(atLeastScratchedPercent>=50){
                scratchCardLayout.onFullReveal();
//                Toast.makeText(getApplicationContext(),"Reaveled",Toast.LENGTH_LONG).show();
                scratchCardLayout3.setScratchEnabled(false);
                scratchCardLayout1.setScratchEnabled(false);
                scratchCardLayout2.setScratchEnabled(false);
                answerid=opt2id;
                if(CorrectAnswerId.equals(ans2)){
                    iscorrect="1";
                }else {
                    iscorrect="0";
                }    nxt_btn.setEnabled(true);
                ansid= ans2;
            }
        }else if(scratchCardLayout.equals(scratchCardLayout3)){
            if(atLeastScratchedPercent>=50){
                scratchCardLayout.onFullReveal();
//                Toast.makeText(getApplicationContext(),"Reaveled",Toast.LENGTH_LONG).show();
                scratchCardLayout1.setScratchEnabled(false);
                scratchCardLayout2.setScratchEnabled(false);
                scratchCardLayout3.setScratchEnabled(false);
                answerid=opt3id;
                if(CorrectAnswerId.equals(ans3)){
                    iscorrect="1";
                }else {
                    iscorrect="0";
                }
                nxt_btn.setEnabled(true);
                answerid = ans3;

            }
        }


    }
    @Override
    public void onScratchComplete() {

    }
    @Override
    public void onclick(String sqid, String cid, String qid, String aid, String iscorrect, String duration) {

    }

    public void Offerdata(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="https://apmmarketing.co.nz/api/GetSmartQuizStatusByCustomer?sqid="+serid+"&cid="+custid;
        Log.d("quixurl "," quizurl : "+url);
        StringRequest movieReq = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("c", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            int IsFinished=obj.getInt("IsFinished");
                            int gameStatus=obj.getInt("GameStatus");
                            final String gametext=obj.getString("StatusMessage");
                                quiz_layout.setVisibility(View.VISIBLE);
                                timerlayout.setVisibility(View.GONE);
                                resultslayout.setVisibility(View.GONE);

                                try {
                                    JSONObject object = new JSONObject(response);
                                    JSONArray  questionarray = object.getJSONArray("Questions");
                                    for(int i =0 ;i<questionarray.length();i++){
                                        List<Drawable> drawablelist = new ArrayList<Drawable>();
                                        drawablelist.clear();
                                        JSONObject objj = questionarray.getJSONObject(i);
                                        JSONArray optionsarray = objj.getJSONArray("answers");
                                        opt1 =  optionsarray.getJSONObject(0).getString("AnswerImagePath");
                                        try {
                                            if (opt1.contains("http")) {
                                                d = drawableFromUrl(opt1);
                                            }else {
                                                d = drawableFromUrl("http://151.106.38.222:92//content/smartquizimages/23/smartquiz-gtcm.jpeg");
                                            }
                                            drawablelist.add(d);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        opt2 =  optionsarray.getJSONObject(1).getString("AnswerImagePath");
                                        try {
                                            if (opt2.contains("http")){
                                                f = drawableFromUrl(opt2);
                                            }else
                                            {
                                                f = drawableFromUrl("http://151.106.38.222:92//content/smartquizimages/23/smartquiz-gtcm.jpeg");
                                            }

                                            drawablelist.add(f);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        opt3 =  optionsarray.getJSONObject(2).getString("AnswerImagePath");
                                        try {
                                            if (opt3.contains("http")){
                                                g= drawableFromUrl(opt3);
                                            }else {
                                                g= drawableFromUrl("http://151.106.38.222:92//content/smartquizimages/23/smartquiz-gtcm.jpeg");
                                            }

                                            drawablelist.add(g);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        drawabledata = drawabledata+drawablelist.toString();
                                        list.add(drawablelist);
                                        Log.d("TAG", "onResponse: "+list.toString());

                                    }
                                    if (databaseHelper.getImagequizdata(serid) == null) {
                                        databaseHelper.insertImageQuestion(serid,questionarray.toString(),"0","0",String.valueOf(System.currentTimeMillis()));
                                    }

                                    datarefresh(serid);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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
                Offerdata();
                //On finish change timer text
            }
        }.
                start();

    }

    private void datarefresh(String s) {
        progress.setVisibility(View.GONE);
        play.setVisibility(View.VISIBLE);
        nxt_btn.setEnabled(false);
        pos= Integer.parseInt(databaseHelper.getImagequizdata(serid).getImposition());
        try {
            JSONArray questionarray = new JSONArray(databaseHelper.getImagequizdata(s).getImquestions());
            if (pos>=questionarray.length()){
                try {
                    JSONArray array = new JSONArray(databaseHelper.getImagequizdata(serid).getImanswer());
                    JSONObject result = new JSONObject();
                    result.put("QuizId",serid);
                    result.put("CustomerId",custid);
                    result.put("Duration",System.currentTimeMillis()-Long.parseLong(databaseHelper.getImagequizdata(serid).getImduration()));
                    int count =0;
                    for (int i=0 ;i<array.length();i++){
                        JSONObject obj = array.getJSONObject(i);
                        if (obj.getString("IsCorrect").equals("1")){
                            count = count+1;
                        }
                    }
                    result.put("CorrectAnsweredCount",count);
                    result.put("AnsweredCount",10);
                    result.put("Answers",array);
                    Log.d("TAG", "onClick: " +result);
                    SubmitAnswers(result.toString());
                } catch (JSONException  e) {
                    e.printStackTrace();
                }

            }else {
                JSONObject obj = questionarray.getJSONObject(pos);
                CorrectAnswerId = questionarray.getJSONObject(pos).getString("CorrectAnswerId");
                JSONArray optionsarray = obj.getJSONArray("answers");
                opt1 =  optionsarray.getJSONObject(0).getString("AnswerImagePath");
                opt2 =  optionsarray.getJSONObject(1).getString("AnswerImagePath");
                opt3 =  optionsarray.getJSONObject(2).getString("AnswerImagePath");
                opt1id =  optionsarray.getJSONObject(0).getString("SmartQuizAnswerId");
                opt2id =  optionsarray.getJSONObject(1).getString("SmartQuizAnswerId");
                opt3id =  optionsarray.getJSONObject(2).getString("SmartQuizAnswerId");
                ans1 =  optionsarray.getJSONObject(0).getString("AnswerNumber");
                ans2 =  optionsarray.getJSONObject(1).getString("AnswerNumber");
                ans3 =  optionsarray.getJSONObject(2).getString("AnswerNumber");
                question.setText(obj.getString("Question"));
                if(CorrectAnswerId.contains("1")){
                    sciv1.setImageDrawable(getResources().getDrawable(R.drawable.right));
                    sciv2.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    sciv3.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                }else
                if(CorrectAnswerId.contains("2")){
                    sciv1.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    sciv2.setImageDrawable(getResources().getDrawable(R.drawable.right));
                    sciv3.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                }else
                if(CorrectAnswerId.contains("3")){
                    sciv1.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    sciv2.setImageDrawable(getResources().getDrawable(R.drawable.wrong));
                    sciv3.setImageDrawable(getResources().getDrawable(R.drawable.right));
                }

                if (android.os.Build.VERSION.SDK_INT > 9)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
                d = (Drawable) list.get(pos).get(0);

                f = (Drawable) list.get(pos).get(1);

                g = (Drawable) list.get(pos).get(2);


                scratchCardLayout1.setScratchDrawable(d);
                scratchCardLayout1.setScratchWidthDip(50f);
                scratchCardLayout1.setScratchEnabled(true);
                scratchCardLayout1.resetScratch();
                scratchCardLayout1.setScratchListener( this);

                scratchCardLayout2.setScratchDrawable(f);
                scratchCardLayout2.setScratchWidthDip(50f);
                scratchCardLayout2.setScratchEnabled(true);
                scratchCardLayout2.resetScratch();
                scratchCardLayout2.setScratchListener( QuizQuestuions.this);

                scratchCardLayout3.setScratchDrawable(g);
                scratchCardLayout3.setScratchWidthDip(50f);
                scratchCardLayout3.setScratchEnabled(true);
                scratchCardLayout3.resetScratch();
                scratchCardLayout3.setScratchListener( this);
            }

//             pos = Integer.parseInt(databaseHelper.getImageData(s).getAnsweredcount());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void SubmitAnswers(String jsonBody){

        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(QuizQuestuions.this);
        String url="https://apmmarketing.co.nz/api/InsertSmartQuizCustomerAllAnswers";
        final String requestBody = jsonBody;
        Log.d("offers","response"+requestBody);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("response","response"+response);
                pDialog.dismiss();
                quiz_layout.setVisibility(View.GONE);
                timerlayout.setVisibility(View.GONE);
                resultslayout.setVisibility(View.VISIBLE);
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray resultsdata = obj.getJSONArray("Results");
                    JSONObject prizedata = obj.getJSONObject("PrizeDetails");
                    if (prizedata.getString("PrizePath").equals("")){
                        Picasso.get().load(R.drawable.betterluck).placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(prizeimage);
                    }else {
                        Picasso.get().load(prizedata.getString("PrizePath")).placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(prizeimage);
                    }//                    Picasso.get().load("http://gamesnatcherz.com/ApplicationFiles/smartquizgameprizes/2/one-cheese-pizza-2741457.jpg").placeholder(R.mipmap.ic_launcher).error(R.drawable.error).into(prizeimage);
 prizetxt.setText(prizedata.getString("PrizeMessage"));
// prizetxt.setText("Won Free Pizza Coupon."+"\n you can see this Prize in your ClaimedPrizes Section");
                    gamestatus.setText((obj.getString("StatusMessage")));
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
                        }
                        adapterr.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
            public Map<String,String> getHeaders() throws AuthFailureError {
                Map<String,String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }




}
