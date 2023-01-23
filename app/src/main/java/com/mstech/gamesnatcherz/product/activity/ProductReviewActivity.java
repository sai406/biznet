package com.mstech.gamesnatcherz.product.activity;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.BaseActivity;
import com.mstech.gamesnatcherz.model.products.RequestAddProductRating;
import com.mstech.gamesnatcherz.model.products.RequestAddProductReview;
import com.mstech.gamesnatcherz.model.products.ResponseAddProductRating;
import com.mstech.gamesnatcherz.retro.ApiClient;
import com.mstech.gamesnatcherz.retro.ApiInterface;
import com.mstech.gamesnatcherz.utils.SharePref;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/** HARISH GADDAM */

public class ProductReviewActivity extends BaseActivity {

    Context mContext = ProductReviewActivity.this;
    SharePref sharePref;

    @BindView(R.id.ivClose)
    ImageView ivClose;

    @BindView(R.id.cLayoutStars1)
    ConstraintLayout cLayoutStars1;
    @BindView(R.id.cLayoutStars2)
    ConstraintLayout cLayoutStars2;
    @BindView(R.id.cLayoutStars3)
    ConstraintLayout cLayoutStars3;
    @BindView(R.id.cLayoutStars4)
    ConstraintLayout cLayoutStars4;
    @BindView(R.id.cLayoutStars5)
    ConstraintLayout cLayoutStars5;

    @BindView(R.id.tvProductName)
    TextView tvTitle;
    @BindView(R.id.tvStars1)
    TextView tvStars1;
    @BindView(R.id.tvStars2)
    TextView tvStars2;
    @BindView(R.id.tvStars3)
    TextView tvStars3;
    @BindView(R.id.tvStars4)
    TextView tvStars4;
    @BindView(R.id.tvStars5)
    TextView tvStars5;

    @BindView(R.id.ratingBar1)
    RatingBar ratingBar1;
    @BindView(R.id.ratingBar2)
    RatingBar ratingBar2;
    @BindView(R.id.ratingBar3)
    RatingBar ratingBar3;
    @BindView(R.id.ratingBar4)
    RatingBar ratingBar4;
    @BindView(R.id.ratingBar5)
    RatingBar ratingBar5;

    @BindView(R.id.tvReviewDescription1)
    TextView tvReviewDescription1;
    @BindView(R.id.tvReviewDescription2)
    TextView tvReviewDescription2;
    @BindView(R.id.tvReviewDescription3)
    TextView tvReviewDescription3;
    @BindView(R.id.tvReviewDescription4)
    TextView tvReviewDescription4;
    @BindView(R.id.tvReviewDescription5)
    TextView tvReviewDescription5;

    @BindView(R.id.etWriteComments)
    EditText etWriteReview;
    @BindView(R.id.btnSubmitComments)
    Button btnSubmitReviewRating;

    private int reviewId = 0;
    private int videoId = 0;
    private int memberId = 0;
    private String title = "";
    private String review = "";

    private int rating = 0;
    private int rating1 = 0;
    private int rating2 = 0;
    private int rating3 = 0;
    private int rating4 = 0;
    private int rating5 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_alert_reviews_ratings);
        ButterKnife.bind(this);
        sharePref = new SharePref(mContext);
        memberId = sharePref.getMemberID();

        ivClose.setOnClickListener(v1 -> {
            onBackPressed();
        });

        getIntentData();
        getUpdateUI(rating);

        // 1
        cLayoutStars1.setOnClickListener(view1 -> {
            getUpdateUI(1);
            apiAddProductRating(0,videoId,sharePref.getMemberID(),"1");
        });

        // 2
        cLayoutStars2.setOnClickListener(view2 -> {
            getUpdateUI(2);
            apiAddProductRating(0,videoId,sharePref.getMemberID(),"2");
        });

        // 3
        cLayoutStars3.setOnClickListener(view3 -> {
            getUpdateUI(3);
            apiAddProductRating(0,videoId,sharePref.getMemberID(),"3");
        });

        // 4
        cLayoutStars4.setOnClickListener(view4 -> {
            getUpdateUI(4);
            apiAddProductRating(0,videoId,sharePref.getMemberID(),"4");
        });

        // 5
        cLayoutStars5.setOnClickListener(view5 -> {
            getUpdateUI(5);
            apiAddProductRating(0,videoId,sharePref.getMemberID(),"5");
        });

        btnSubmitReviewRating.setOnClickListener(v2 -> {
            review = etWriteReview.getText().toString();
            if (isNetworkConnected()) {
                Log.e("ReviewId: ", "" + reviewId);
                Log.e("VideoId: ", "" + videoId);
                Log.e("MemberId: ", "" + sharePref.getMemberID());
                Log.e("Review: ", etWriteReview.getText().toString());
                Log.e("Stars: ", "" + rating);
                Log.e("Action: ", "0");
                apiAddReview(reviewId,videoId,sharePref.getMemberID(),etWriteReview.getText().toString(),1);
            }
        });
    }

    private void getIntentData() {
        videoId = getIntent().getIntExtra("videoId", 0);
        reviewId = getIntent().getIntExtra("reviewId", 0);
        title = getIntent().getStringExtra("title");
        rating = (int) getIntent().getFloatExtra("rating", 0);
        review = getIntent().getStringExtra("review");
        tvTitle.setText(title);
        etWriteReview.setText(review);
    }

    private void getUpdateUI(int stars) {
        if (stars == 0) {
            rating = stars;
            cLayoutStars1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars5.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));

        } else if (stars == 1) {
            rating = (int) ratingBar1.getRating();
            cLayoutStars1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorYellow1));
            cLayoutStars2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars5.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (stars == 2) {
            rating = (int) ratingBar2.getRating();
            cLayoutStars2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorYellow1));
            cLayoutStars1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars5.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (stars == 3) {
            rating = (int) ratingBar3.getRating();
            cLayoutStars3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorYellow1));
            cLayoutStars1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars5.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (stars == 4) {
            rating = (int) ratingBar4.getRating();
            cLayoutStars4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorYellow1));
            cLayoutStars1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars5.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        } else if (stars == 5) {
            rating = (int) ratingBar5.getRating();
            cLayoutStars5.setBackgroundColor(ContextCompat.getColor(mContext, R.color.colorYellow1));
            cLayoutStars1.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars2.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars3.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
            cLayoutStars4.setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
        }
    }

    /** ----------------------------------- API CALL Add Review --------------------------------- */
    private void apiAddReview(int reviewId, int productId, int memberId, String review, int action) {
        showPDialog(getString(R.string.loading));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestAddProductReview addProductReview = new RequestAddProductReview(reviewId, productId, memberId, review, action);

        Call<ResponseAddProductRating> call = apiInterface.apiPOSTAddProductReview(addProductReview);
        call.enqueue(new Callback<ResponseAddProductRating>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAddProductRating> call, @NonNull Response<ResponseAddProductRating> response) {
                hidePDialog();

                if (isApiSuccess(response.code())) {
                    assert response.body() != null;
                    Log.d("TAG", "onResponse: "+response.body());
                    showToast("Review send Successfully.");
                } else {
                    showToast("An error has occurred.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAddProductRating> call, @NonNull Throwable t) {
                hidePDialog();
            }
        });
    }

    /** --------------------------- API CALL Add Rating ------------------------------*/
    //TODO CHANGE ratingId, after api service is worked.
    private void apiAddProductRating(int ratingId, int productId, int memberId, String rating) {
        float starRating = Integer.parseInt(rating);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestAddProductRating addProductRating = new RequestAddProductRating(ratingId, productId, memberId, rating);
        Log.d("TAG", "apiAddProductRating: "+addProductRating.getRating());
        Call<ResponseAddProductRating> call = apiInterface.apiPOSTAddProductRating(addProductRating);
        call.enqueue(new Callback<ResponseAddProductRating>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAddProductRating> call, @NonNull Response<ResponseAddProductRating> response) {

                if (isApiSuccess(response.code())) {
                    Log.d("TAG", "onResponse: "+response.body());
                    showToast("Rating send Successfully.");
                } else {
                    showToast("An error has occurred.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAddProductRating> call, @NonNull Throwable t) {
            }
        });
    }


    // backend is accept only integer value for rating stars
//    private void apiGETAddReviewRating(int reviewid, int videoid, int mid, String review, int rating, int action) {
//        showPDialog(getString(R.string.loading));
//        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
//        Call<ResponseAddReviewRating> call = apiInterface.apiGETAddVideoReviewRating(reviewid, videoid, mid, review, rating, action);
//        call.enqueue(new Callback<ResponseAddReviewRating>() {
//            @Override
//            public void onResponse(@NonNull Call<ResponseAddReviewRating> call, @NonNull Response<ResponseAddReviewRating> response) {
//                hidePDialog();
//                Log.e("AddReview-->", "" + response.code());
//
//                if (isApiSuccess(response.code())) {
//                    assert response.body() != null;
//                    if (response.body().getStatusCode() > 0) {
//                        showToast((response.body().getStatusMessage().isEmpty()) ? "" : response.body().getStatusMessage());
//                        onBackPressed();
//                    }
//                } else {
//                    showToast("An error has occurred.");
//                }
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<ResponseAddReviewRating> call, @NonNull Throwable t) {
//                hidePDialog();
//            }
//        });
//    }
}