package com.mstech.gamesnatcherz.product.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.SPStaticUtils;
import com.google.android.material.button.MaterialButton;
import com.mstech.gamesnatcherz.model.SharedKey;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.BaseActivity;
import com.mstech.gamesnatcherz.activities.LoginActivity;
import com.mstech.gamesnatcherz.model.products.AttributeValue;
import com.mstech.gamesnatcherz.model.products.POJOStaticDataWriteRatingReview;
import com.mstech.gamesnatcherz.model.products.RequestAddProductRating;
import com.mstech.gamesnatcherz.model.products.RequestAddProductReview;
import com.mstech.gamesnatcherz.model.products.ResponseAddProductRating;
import com.mstech.gamesnatcherz.model.products.ResponseAddToCartItems;
import com.mstech.gamesnatcherz.model.products.ResponseGETProduct;
import com.mstech.gamesnatcherz.model.products.ResponseGetProductOverallRatings;
import com.mstech.gamesnatcherz.model.products.ResponseGetProductSize;
import com.mstech.gamesnatcherz.model.products.ResponseProductReviews;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.RequestAddToCartItems;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.ResponseGetCartItem;
import com.mstech.gamesnatcherz.product.adapter.ProductSizeAdapter;
import com.mstech.gamesnatcherz.product.adapter.RatingReviewsAdapter;
import com.mstech.gamesnatcherz.product.adapter.ViewPagerAdapter;
import com.mstech.gamesnatcherz.retro.ApiClient;
import com.mstech.gamesnatcherz.retro.ApiInterface;
import com.mstech.gamesnatcherz.utils.SharePref;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HARISH GADDAM
 */

public class ProductDetailsPassObjectActivity extends BaseActivity {

    Context mContext = ProductDetailsPassObjectActivity.this;
    SharePref sharePref;

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivCart)
    ImageView ivCart;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.layoutDots)
    LinearLayout sliderDotspanel;

    //    @BindView(R.id.view) View view;
    @BindView(R.id.tvHeader)
    TextView tvHeader;
    @BindView(R.id.tvAddedCartValue)
    TextView tvAddedCartValue;
    @BindView(R.id.tvSize)
    TextView tvSize;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.tvConditionsValue)
    TextView tvConditionsValue;
    @BindView(R.id.tvPrice)
    TextView tvPrice;

    @BindView(R.id.actual_price)
    TextView actualPrice;
    @BindView(R.id.tvOverallRatingValue)
    TextView tvOverallRatingValue;
    @BindView(R.id.tvEmptyReviews)
    TextView tvEmptyReviews;

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.tvRatingBarValue)
    TextView tvRatingBarValue;

    @BindView(R.id.btnWriteReview)
    MaterialButton btnWriteReview;
    @BindView(R.id.btnAddToCart)
    MaterialButton btnAddToCart;
    @BindView(R.id.btnBuyNow)
    MaterialButton btnBuyNow;

    @BindView(R.id.rvSize)
    RecyclerView rvSize;
    @BindView(R.id.rvReviews)
    RecyclerView rvReviews;

    private int dotscount;
    private ImageView[] dots;
    private ResponseGETProduct product;
    private ArrayList<String> images;

    private final String strQuantityValue = "";
    private String strSizeColorValue = "";
    private String strSizeValue = "";
    private final String strColorValue = "";
    private String strTotalPriceValue = "";
    private final String strStatusValue = "";

    int productId = 0;
    int orgId = 0;
    int cartCount = 0;
    int isSelectedSize = 0;
    int isReviewId = 0;
    int reviewid = 0;
    public int strIsReviewMemberId = 0;
    public float rating;

    public String strIsTitle = "";
    public String strIsReview = "";
    public String strWriteRatingReviewStars = "";
    public String strWriteRatingReviewRatingBar = "";
    public String strWriteRatingReviewRatingBarDescription = "";
    public String strWriteRatingReviewComments = "";

    private onClickInterface onClickInterface;
    ArrayList<POJOStaticDataWriteRatingReview> arrayListWriteRatingReview = new ArrayList<>();

    private final ArrayList<AttributeValue> arrayListProductColorAttributeValue = new ArrayList<>();
    private final ArrayList<AttributeValue> arrayListProductSizeAttributeValues = new ArrayList<>();
    private final ArrayList<ResponseProductReviews> arrayListProductReviews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);
        sharePref = new SharePref(mContext);
        hideKeyboard();
        getSupportActionBar().hide();
        ivCart.setVisibility(View.VISIBLE);
        tvAddedCartValue.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        tvHeader.setText(R.string.product_details);

        product = new ResponseGETProduct();
        images = new ArrayList<>();
        product = (ResponseGETProduct) getIntent().getSerializableExtra("product_details");
        assert product != null;
        productId = product.getProductId();
//        orgId = product.getOrganisationId();
        strTotalPriceValue = product.getPrice();

        images.add(product.getProductImagePath());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(8, 0, 8, 0);
            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tvTitle.setText(product.getProductName());
        strIsTitle = product.getProductName();
        tvDescription.setText(product.getDescription());
        if (product.getDisplayprice().equals(product.getDisplayofferprice())) {
            actualPrice.setVisibility(View.GONE);
        }
        actualPrice.setText(product.getDisplayprice());
        actualPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvPrice.setText(product.getDisplayofferprice());

        /** ---------------------------- API CALL Get Product Sizes ------------------------------*/
        if (isNetworkConnected()) {
//            getProductSizes(productId);
            /** API CALL --> Get product reviews */
//            apiGETProductReviews(productId, sharePref.getMemberID());

            /** API CALL --> Get product ratings */
//            apiGETProductRatings(productId);
        }

        onClickInterface = position -> {
            isSelectedSize = position;
//            Toast.makeText(mContext, "Selected Size: " + arrayListProductSizeAttributeValues.get(isSelectedSize).getAttributeValue(), Toast.LENGTH_SHORT).show();
            strSizeColorValue = "Size:" + arrayListProductSizeAttributeValues.get(isSelectedSize).getAttributeValue() + ",Color:White"; //Size:x,Color:White
            strSizeValue = arrayListProductSizeAttributeValues.get(isSelectedSize).getAttributeValue();
        };

        rvSize.setHasFixedSize(false);
        rvSize.setNestedScrollingEnabled(false);
        rvSize.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        rvReviews.setHasFixedSize(false);
        rvReviews.setNestedScrollingEnabled(false);
        rvReviews.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        /** -------------------------------- Write review function ----------------------------- */
        btnWriteReview.setOnClickListener(view -> {
            startActivity(new Intent(mContext, ProductReviewActivity.class).putExtra("videoId", productId).putExtra("reviewId", reviewid).putExtra("title", strIsTitle).putExtra("rating", rating).putExtra("review", strIsReview));
        });

        /** API CALL --> ADD ITEMS TO CART */
        btnAddToCart.setOnClickListener(view -> {
            if (SPStaticUtils.getString(SharedKey.INSTANCE.getISLOGIN()).equals("true")) {
                apiCallAddToCart(1);
            } else {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        });

        /** API CALL --> ORDER NOW */
        btnBuyNow.setOnClickListener(view -> {
            if (SPStaticUtils.getString(SharedKey.INSTANCE.getISLOGIN()).equals("true")) {
                apiCallAddToCart(2);
            } else {
                startActivity(new Intent(this, LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (isNetworkConnected()) {
            apiCallGetCartItems();
        }
    }

    /**
     * ----------------------------------- API CALL Add Review ---------------------------------
     */
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

    /**
     * --------------------------- API CALL Add Rating ------------------------------
     */
    //TODO CHANGE ratingId, after api service is worked.
    private void apiAddProductRating(int ratingId, int productId, int memberId, String rating) {
        float starRating = Integer.parseInt(rating);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestAddProductRating addProductRating = new RequestAddProductRating(ratingId, productId, memberId, rating);
        Call<ResponseAddProductRating> call = apiInterface.apiPOSTAddProductRating(addProductRating);
        call.enqueue(new Callback<ResponseAddProductRating>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAddProductRating> call, @NonNull Response<ResponseAddProductRating> response) {

                if (isApiSuccess(response.code())) {
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

    /**
     * ---------------------------- API CALL Get Product Sizes ------------------------------
     */
    private void getProductSizes(int productId) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<ResponseGetProductSize>> call = apiInterface.apiGETProductSizes(productId);
        call.enqueue(new Callback<ArrayList<ResponseGetProductSize>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ResponseGetProductSize>> call, @NonNull Response<ArrayList<ResponseGetProductSize>> response) {
                Log.e("GetProducts-->", "" + response.code());

                arrayListProductColorAttributeValue.clear();
                arrayListProductSizeAttributeValues.clear();
                if (isApiSuccess(response.code())) {
                    assert response.body() != null;
                    if (response.body().size() > 0) {

                        for (int i = 0; i < response.body().size(); i++) {
                            if (response.body().get(i).getAttribute().equalsIgnoreCase("Size")) {
                                arrayListProductSizeAttributeValues.addAll(response.body().get(i).getAttributeValues());
                            } else if (response.body().get(i).getAttribute().equalsIgnoreCase("Color")) {
                                arrayListProductColorAttributeValue.addAll(response.body().get(i).getAttributeValues());
                            }
                        }
                        ProductSizeAdapter sizeAdapter = new ProductSizeAdapter(mContext, arrayListProductSizeAttributeValues, onClickInterface);
                        rvSize.setAdapter(sizeAdapter);
                        if (arrayListProductSizeAttributeValues.isEmpty()) {
                            tvSize.setVisibility(View.INVISIBLE);
                        }
                    } else {
                        Toast.makeText(ProductDetailsPassObjectActivity.this, "No sizes found at the moment.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showToast("An error has occurred.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ResponseGetProductSize>> call, @NonNull Throwable t) {
            }
        });
    }

    /**
     * --------------------------------- Reviews  ---------------------------------------------
     */
    private void apiGETProductReviews(int productId, int mid) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        JSONObject body = new JSONObject();
        try {
            body.put("ProductId", productId);
            body.put("CustomerId", mid);
            body.put("PageIndex", "1");
            body.put("PageSize", "-1");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ResponseBody finalbody = ResponseBody.create("application/json; charset=utf-8", MediaType.parse(((body)).toString()));
        Log.d("TAG", "apiGETProductReviews: " + body);
        Call<ArrayList<ResponseProductReviews>> call = apiInterface.apiPOSTGetProductReviews(finalbody);
        call.enqueue(new Callback<ArrayList<ResponseProductReviews>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ResponseProductReviews>> call, @NonNull Response<ArrayList<ResponseProductReviews>> response) {
                Log.e("GetReviews-->", "" + response.code());

                arrayListProductReviews.clear();
                if (isApiSuccess(response.code())) {
                    assert response.body() != null;
                    if (response.body().size() > 0) {
                        tvEmptyReviews.setVisibility(View.GONE);
                        rvReviews.setVisibility(View.VISIBLE);
                        arrayListProductReviews.addAll(response.body());
                        RatingReviewsAdapter ratingReviewsAdapter = new RatingReviewsAdapter(mContext, response.body());
                        rvReviews.setAdapter(ratingReviewsAdapter);
                        ratingReviewsAdapter.notifyDataSetChanged();

                        for (int i = 0; i < arrayListProductReviews.size(); i++) {
                            isReviewId = arrayListProductReviews.get(i).getIsReviewd();
                            strIsReviewMemberId = arrayListProductReviews.get(i).getMemberId();

                            if (isReviewId == 1 && strIsReviewMemberId == sharePref.getMemberID()) {
                                strIsReview = arrayListProductReviews.get(i).getReview();
                                reviewid = arrayListProductReviews.get(i).getReviewId();
//                                rating = arrayListProductReviews.get(i).getRating(); // rating is null from server.
                                rating = 0;
                                Log.e("strIsReview: ", strIsReview);
                            }
                        }

                    } else {
                        tvEmptyReviews.setVisibility(View.VISIBLE);
                        rvReviews.setVisibility(View.GONE);
                    }
                } else {
                    showToast("An error has occurred.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ResponseProductReviews>> call, @NonNull Throwable t) {
                Log.e("onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }

    /**
     * ---------------------------- Overall ratings  ------------------------------------------
     */
    private void apiGETProductRatings(int productId) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseGetProductOverallRatings> call = apiInterface.apiPOSTGetProductOverallRatings(productId);
        call.enqueue(new Callback<ResponseGetProductOverallRatings>() {
            @Override
            public void onResponse(@NonNull Call<ResponseGetProductOverallRatings> call, @NonNull Response<ResponseGetProductOverallRatings> response) {
                Log.e("GetRatings-->", "" + response.code());

                if (isApiSuccess(response.code())) {
                    assert response.body() != null;

                    if (response.body().getTotalRating() != 0.0) {
                        tvOverallRatingValue.setText(response.body().getTotalRating() + " out of 5 stars");
                        ratingBar.setRating(response.body().getTotalRating());
                    }
                    if (response.body().getTotalMembers() != 0) {
                        tvRatingBarValue.setText("(" + response.body().getTotalMembers() + ")");
                    }
                } else {
                    showToast("An error has occurred.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseGetProductOverallRatings> call, @NonNull Throwable t) {
                Log.e("onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }

    /**
     * API Implementation --> ADD ITEMS TO CART
     */
    private void apiCallAddToCart(int isAddToCartORBuyNow) {
        if (!arrayListProductSizeAttributeValues.isEmpty() && strSizeValue.isEmpty()) {
            showToast("Select Size");
        } else {
            showPDialog(getString(R.string.loading));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            RequestAddToCartItems addToCartModel = new RequestAddToCartItems(0, 0, productId, product.getBusinessId(), sharePref.getMemberID(), 1, strTotalPriceValue, strSizeColorValue, 1);

            Call<ResponseAddToCartItems> call = apiInterface.apiPOSTAddRemoveProductCartItems(addToCartModel);
            call.enqueue(new Callback<ResponseAddToCartItems>() {
                @Override
                public void onResponse(@NonNull Call<ResponseAddToCartItems> call, @NonNull Response<ResponseAddToCartItems> response) {
                    hidePDialog();
                    if (response.body().getResult() > 0) {
                        showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
                        if (isAddToCartORBuyNow == 1) {
                            apiCallGetCartItems();
                        } else {
                            startActivity(new Intent(mContext, ItemsInCartActivity.class));
                        }
                    } else {
                        showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseAddToCartItems> call, @NonNull Throwable t) {
                    hidePDialog();
                }
            });
        }

    }

    /**
     * API Implementation --> GET CART ITEMS
     */
    public void apiCallGetCartItems() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<ResponseGetCartItem>> call = apiInterface.apiGETProductItemsInCart(SPStaticUtils.getString("customerid", "0"), SPStaticUtils.getString("businessid", "0"));
        call.enqueue(new Callback<ArrayList<ResponseGetCartItem>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ResponseGetCartItem>> call, @NonNull Response<ArrayList<ResponseGetCartItem>> response) {

                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        for (int i = 0; i < response.body().size(); i++) {
                            cartCount = response.body().get(i).getTotalQty();
                            Log.e(" cartQty-->", "" + cartCount);
                        }

                        if (cartCount > 0) {
                            tvAddedCartValue.setText(String.valueOf(cartCount));
                            ivCart.setOnClickListener(view -> {
                                startActivity(new Intent(mContext, ItemsInCartActivity.class));
                            });
                        }
                    } else {
                        tvAddedCartValue.setText("");
                    }
                } else {
                    tvAddedCartValue.setText("");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ResponseGetCartItem>> call, @NonNull Throwable t) {
                Log.e(" onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }
}