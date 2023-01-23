package com.mstech.gamesnatcherz.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.adapter.BasketProductsAdapter;
import com.mstech.gamesnatcherz.model.Basketproducts;
import com.mstech.gamesnatcherz.retro.ApiClient;
import com.mstech.gamesnatcherz.retro.ApiInterface;
import com.mstech.gamesnatcherz.utils.SharePref;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HARISH GADDAM
 */

public class BasketActivity extends AppCompatActivity {

    private final BasketActivity mContext = BasketActivity.this;


    @BindView(R.id.ivBack)
    ImageView ivBack;

    @BindView(R.id.tvHeader)
    TextView tvHeader;


    //    @BindView(R.id.rvTabs)
    RecyclerView rvTabs;

    private final int orgId = 1;

    private final int previousTotal = 0;
    private final boolean loading = true;
    private final int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManagerProducts;
    SharedPreferences sharedPreferences;
    SharePref sharePref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baskett);
        ButterKnife.bind(this);
        sharePref = new SharePref(this);
        rvTabs = findViewById(R.id.rvTabs);
        getSupportActionBar().hide();
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tvHeader.setText("Orders");

//        tvHeader.setText(R.string.);
        mLayoutManagerProducts = new LinearLayoutManager(this);
        rvTabs.setLayoutManager(mLayoutManagerProducts);
        apiBasketProducts(sharePref.getMemberID() + "", orgId);

    }


    private void apiBasketProducts(String memberId, int orgId) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Basketproducts>> call = apiInterface.apiGETBasketProductOrders(memberId, "1");
        call.enqueue(new Callback<ArrayList<Basketproducts>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Basketproducts>> call, @NonNull Response<ArrayList<Basketproducts>> response) {

                Log.e("BasketProducts-->", "" + response.body());

                if (response.body().size() > 0) {
                    ArrayList<Basketproducts> list = response.body();
                    Collections.reverse(list);
                    BasketProductsAdapter mAdapter = new BasketProductsAdapter(BasketActivity.this, list);
                    rvTabs.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Basketproducts>> call, @NonNull Throwable t) {
                Log.e("onFailure-->", "" + t.getLocalizedMessage());

            }
        });
    }


}