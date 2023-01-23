package com.mstech.gamesnatcherz.product.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPStaticUtils;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.BaseActivity;
import com.mstech.gamesnatcherz.activities.BasketActivity;
import com.mstech.gamesnatcherz.model.products.RequestBuyNow;
import com.mstech.gamesnatcherz.model.products.ResponseOrderProducts;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.CartItem;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.ResponseGetCartItem;
import com.mstech.gamesnatcherz.product.adapter.FinalOrderAdapter;
import com.mstech.gamesnatcherz.retro.ApiClient;
import com.mstech.gamesnatcherz.retro.ApiInterface;
import com.mstech.gamesnatcherz.utils.SharePref;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalOrderActivity extends BaseActivity {

    private final Context mContext = FinalOrderActivity.this;
    SharePref sharePref;

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvHeader)
    TextView tvHeader;
    @BindView(R.id.rvItemsInCart)
    RecyclerView rvItemsInCart;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.subtotal)
    TextView subtotal;
    @BindView(R.id.shipping)
    TextView shipping;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.confirm)
    Button btnOrderNow;

    FinalOrderAdapter mAdapter;

    int cartId;
    int orgId;
    int memberId;
    int totalQty;
    String addresstext, pincode;

    private final String strTotalPriceValue = "";
    private final String strSizeColorValue = "";
    String strTotalDisplayPrice = "";

    private final ArrayList<ResponseGetCartItem> arrayListItemsInCart = new ArrayList<ResponseGetCartItem>();
    private final ArrayList<CartItem> cartList = new ArrayList<CartItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_order);
        ButterKnife.bind(this);
        sharePref = new SharePref(mContext);
        tvHeader.setText("Confirm Order");
        addresstext = getIntent().getStringExtra("address");
        pincode = getIntent().getStringExtra("pincode");
        memberId = sharePref.getMemberID();
        address.setText("Shipping Address" + "\n" + addresstext + "\n" + pincode);
        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        tvHeader.setText(R.string.items_in_cart);
        rvItemsInCart.setHasFixedSize(false);
        rvItemsInCart.setNestedScrollingEnabled(false);

        rvItemsInCart.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
//        apicall
        if (isNetworkConnected()) {
            apiCallGetCartItems();
        }

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param

        btnOrderNow.setOnClickListener(view -> {
            if (isNetworkConnected()) {
                apiOrderProducts(cartId);
            }
        });
    }

    /**
     * API Implementation --> GET CART ITEMS
     */
    public void apiCallGetCartItems() {
        showPDialog(getString(R.string.loading));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<ResponseGetCartItem>> call = apiInterface.apiGETProductItemsInCart(SPStaticUtils.getString("customerid", "0"), SPStaticUtils.getString("businessid", "0"));
        call.enqueue(new Callback<ArrayList<ResponseGetCartItem>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ResponseGetCartItem>> call, @NonNull Response<ArrayList<ResponseGetCartItem>> response) {
                hidePDialog();
                Log.e("ItemsInCart-->", "" + response.code());

                arrayListItemsInCart.clear();
                if (isApiSuccess(response.code())) {
                    assert response.body() != null;
                    if (response.body().size() > 0) {
                        arrayListItemsInCart.addAll(response.body());
                        cartList.clear();
                        shipping.setText(arrayListItemsInCart.get(0).getDisplayShippingCharge());
                        total.setText(arrayListItemsInCart.get(0).getDisplayTotalPrice());
                        subtotal.setText(arrayListItemsInCart.get(0).getDisplayTotalPrice());
                        for (int i = 0; i < arrayListItemsInCart.size(); i++) {
                            cartId = arrayListItemsInCart.get(i).getCartId();
                            orgId = arrayListItemsInCart.get(i).getBusinessId();
                            totalQty = arrayListItemsInCart.get(i).getTotalQty();
                            strTotalDisplayPrice = arrayListItemsInCart.get(i).getDisplayTotalPrice();
                            cartList.addAll(arrayListItemsInCart.get(i).getCartItems());
                        }

                        if (!arrayListItemsInCart.isEmpty()) {
                            mAdapter = new FinalOrderAdapter(mContext, orgId, totalQty, strTotalDisplayPrice, cartList);
                            rvItemsInCart.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        total.setText("");
                        final Dialog dialog = new Dialog(mContext);
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        dialog.setCancelable(false);
                        dialog.setContentView(R.layout.layout_alert_dialog);
                        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                        TextView tvQuantity = dialog.findViewById(R.id.tvQuantity);
                        tvQuantity.setText(R.string.empty_items_in_product_cart);
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
            public void onFailure(@NonNull Call<ArrayList<ResponseGetCartItem>> call, @NonNull Throwable t) {
                Log.e(" onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */

    /**
     * ----------------- API Implementation --> After Buy Now is done. then call Order Products and navigate to ItemsInCart ---------------------------
     */
    private void apiOrderProducts(int cartId) {
        showPDialog(getString(R.string.loading));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestBuyNow requestBuyNow = new RequestBuyNow(cartId, pincode, addresstext, shipping.getText().toString());
        Call<ResponseOrderProducts> call = apiInterface.apiPOSTOrderProducts(requestBuyNow);
        call.enqueue(new Callback<ResponseOrderProducts>() {
            @Override
            public void onResponse(@NonNull Call<ResponseOrderProducts> call, @NonNull Response<ResponseOrderProducts> response) {
                hidePDialog();
                assert response.body() != null;
                Log.e("OrderProducts-->", "" + response.body().getOrderId());
                if (response.body().getOrderId() > 0) {
//                    showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
//                    startActivity(new Intent(mContext, WebViewWithNavigation.class)
//                            .putExtra("url", "http://www.solutionsempo.com/Product/Payment?mid="+memberId+"&oid="+response.body().getOrderId()));
                    startActivity(new Intent(FinalOrderActivity.this, BasketActivity.class));
                    finish();
                } else {
                    showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseOrderProducts> call, @NonNull Throwable t) {
                hidePDialog();
                Log.e(" onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }
}