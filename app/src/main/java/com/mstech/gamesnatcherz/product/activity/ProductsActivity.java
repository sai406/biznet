package com.mstech.gamesnatcherz.product.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.BaseActivity;
import com.mstech.gamesnatcherz.model.products.RequestGetProducts;
import com.mstech.gamesnatcherz.model.products.ResponseGETProduct;
import com.mstech.gamesnatcherz.product.adapter.ProductsAdapter;
import com.mstech.gamesnatcherz.retro.ApiClient;
import com.mstech.gamesnatcherz.retro.ApiInterface;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HARISH GADDAM
 */

public class ProductsActivity extends BaseActivity {

    private final Context mContext = ProductsActivity.this;

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.etSearch)
    EditText etSearch;
    @BindView(R.id.rvLiveStreamingVideos)
    RecyclerView rvProducts;

    ProductsAdapter mAdapter;

    private final ArrayList<ResponseGETProduct> arrayListProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_streaming_videos_search);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        if (isNetworkConnected()) {
            apiProductList();
        }
        etSearch.setVisibility(View.VISIBLE);
        etSearch.setHint(R.string.hint_search_products);
        this.etSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                ProductsActivity.this.filterQuery(editable.toString());
            }
        });


        rvProducts.setHasFixedSize(false);
        rvProducts.setNestedScrollingEnabled(false);
        rvProducts.setLayoutManager(new GridLayoutManager(mContext, 2));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void apiProductList() {
        showPDialog(getString(R.string.loading));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestGetProducts products = new RequestGetProducts(
                "",
                -1,
                1
        );
        Call<ArrayList<ResponseGETProduct>> call = apiInterface.apiPOSTGetProducts(products);
        call.enqueue(new Callback<ArrayList<ResponseGETProduct>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ResponseGETProduct>> call, @NonNull Response<ArrayList<ResponseGETProduct>> response) {
                hidePDialog();
                Log.e("GetProducts-->", "" + response.code());

                arrayListProducts.clear();
                if (isApiSuccess(response.code())) {
                    assert response.body() != null;
                    if (response.body().size() > 0) {
                        arrayListProducts.addAll(response.body());
                        mAdapter = new ProductsAdapter(mContext, arrayListProducts);
                        rvProducts.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();
                    } else {
                        final Dialog dialog = new Dialog(mContext);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.layout_alert_dialog);
                        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        TextView tvQuantity = dialog.findViewById(R.id.tvQuantity);
                        tvQuantity.setText("No products found at the moment.");
                        dialog.findViewById(R.id.tvOK).setOnClickListener(view -> {
                            dialog.dismiss();
                            onBackPressed();
                        });
                        dialog.show();
                    }
                } else {
                    showToast("An error has occurred.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ResponseGETProduct>> call, @NonNull Throwable t) {
                Log.e("onFailure-->", "" + t.getLocalizedMessage());
                hidePDialog();
            }
        });
    }

    /* access modifiers changed from: private  Youtube Api Key = AIzaSyBX8yKPaIoPIuN8eWJiT3c-2SHDmVMaueo */
    public void filterQuery(String text) {
        ArrayList<ResponseGETProduct> filterdNames = new ArrayList<>();
        for (ResponseGETProduct s : this.arrayListProducts) {
            if (s.getProductName().toLowerCase().contains(text) || s.getProductName().toUpperCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.mAdapter.setFilter(filterdNames);
    }
}