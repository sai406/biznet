package com.mstech.gamesnatcherz.product.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SPStaticUtils;
import com.google.android.material.button.MaterialButton;
import com.mstech.gamesnatcherz.R;
import com.mstech.gamesnatcherz.activities.BaseActivity;
import com.mstech.gamesnatcherz.model.products.ResponseAddToCartItems;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.CartItem;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.RequestAddToCartItems;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.ResponseGetCartItem;
import com.mstech.gamesnatcherz.product.adapter.ItemsInCartAdapter;
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

/**
 * HARISH GADDAM
 */

public class ItemsInCartActivity extends BaseActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private final Context mContext = ItemsInCartActivity.this;
    SharePref sharePref;

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvHeader)
    TextView tvHeader;
    @BindView(R.id.rvItemsInCart)
    RecyclerView rvItemsInCart;
    @BindView(R.id.tvTotalPrice)
    TextView tvTotalPrice;
    @BindView(R.id.btnOrderNow)
    MaterialButton btnOrderNow;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    ItemsInCartAdapter mAdapter;

    int cartId;
    int orgId;
    int memberId;
    int totalQty;

    private final String strTotalPriceValue = "";
    private final String strSizeColorValue = "";
    String strTotalDisplayPrice = "";

    private final ArrayList<ResponseGetCartItem> arrayListItemsInCart = new ArrayList<ResponseGetCartItem>();
    private final ArrayList<CartItem> cartList = new ArrayList<CartItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_in_cart);
        ButterKnife.bind(this);
        sharePref = new SharePref(mContext);
        getSupportActionBar().hide();
        memberId = sharePref.getMemberID();

        ivBack.setOnClickListener(view -> {
            onBackPressed();
        });
        tvHeader.setText(R.string.items_in_cart);
        rvItemsInCart.setHasFixedSize(false);
        rvItemsInCart.setNestedScrollingEnabled(false);
        rvItemsInCart.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        // adding item touch helper
        // only ItemTouchHelper.LEFT added to detect Right to Left swipe
        // if you want both Right -> Left and Left -> Right
        // add pass ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT as param
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rvItemsInCart);

//        apicall
        if (isNetworkConnected()) {
            apiCallGetCartItems();
        }

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback1 = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        // attaching the touch helper to recycler view
        new ItemTouchHelper(itemTouchHelperCallback1).attachToRecyclerView(rvItemsInCart);

        btnOrderNow.setOnClickListener(view -> {
            if (isNetworkConnected()) {
                startActivity(new Intent(ItemsInCartActivity.this, AddressActivity.class).putExtra("cartiid", cartId).putExtra("from", "product").putExtra("subtotal", strTotalDisplayPrice));
//                apiOrderProducts(cartId);
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
                        for (int i = 0; i < arrayListItemsInCart.size(); i++) {
                            tvTotalPrice.setText("Total Price  : " + arrayListItemsInCart.get(i).getDisplayTotalPrice());
                            cartId = arrayListItemsInCart.get(i).getCartId();
                            orgId = arrayListItemsInCart.get(i).getBusinessId();
                            totalQty = arrayListItemsInCart.get(i).getTotalQty();
                            strTotalDisplayPrice = arrayListItemsInCart.get(i).getDisplayTotalPrice();
                            cartList.addAll(arrayListItemsInCart.get(i).getCartItems());
                        }

                        if (!arrayListItemsInCart.isEmpty()) {
                            mAdapter = new ItemsInCartAdapter(mContext, orgId, totalQty, strTotalDisplayPrice, cartList);
                            rvItemsInCart.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    } else {
                        tvTotalPrice.setText("");
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
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof ItemsInCartAdapter.MyViewHolder) {

//            apiPOSTDeleteCartItem(cartList.get(position).getCartItemId(),
//                    cartList.get(position).getCartId(),
//                    cartList.get(position).getProductId(),
//                    orgId,
//                    memberId,
//                    cartList.get(position).getQty(),
//                    cartList.get(position).getPrice(),
//                    cartList.get(position).getDealAttributeValues(),
//                    -1
//            );
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            RequestAddToCartItems addToCartModel = new RequestAddToCartItems(
                    cartList.get(position).getCartItemId(),
                    cartList.get(position).getCartId(),
                    cartList.get(position).getProductId(),
                    orgId,
                    sharePref.getMemberID(),
                    0,
                    cartList.get(position).getPrice().toString(),
                    cartList.get(position).getDealAttributeValues(),
                    -1
            );

            Call<ResponseAddToCartItems> call = apiInterface.apiPOSTAddRemoveProductCartItems(addToCartModel);
            call.enqueue(new Callback<ResponseAddToCartItems>() {
                @Override
                public void onResponse(@NonNull Call<ResponseAddToCartItems> call, @NonNull Response<ResponseAddToCartItems> response) {
                    Log.e(" minus_quantity-->", "" + response.code());
                    assert response.body() != null;
                    if (response.body().getResult() > 0) {
                        Toast.makeText(mContext, "Removed Successfully!.", Toast.LENGTH_SHORT).show();
                        ((ItemsInCartActivity) mContext).apiCallGetCartItems();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ResponseAddToCartItems> call, @NonNull Throwable t) {
                    Log.e(" onFailure-->", "" + t.getLocalizedMessage());
                }
            });
            // get the removed item name to display it in snack bar
            /*String name = cartList.get(viewHolder.getAdapterPosition()).getProductName();

            // backup of removed item for undo purpose
            final CartItem deletedItem = cartList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();*/

            // remove the item from recycler view
//            mAdapter.deleteItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            /*Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
//                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();*/
        }
    }

    /**
     * ------------------------ API DELETE CART ITEM ----------------------------
     */
    private void apiPOSTDeleteCartItem(int cartItemId, int cartId, int productId, int orgId, int memberId, int qty, Double price, String dealAttributeValues, int action) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        RequestAddToCartItems addToCartModel = new RequestAddToCartItems(
                0,
                0,
                productId,
                orgId,
                memberId,
                1,
                strTotalPriceValue,
                strSizeColorValue,
                1
        );

        Call<ResponseAddToCartItems> call = apiInterface.apiPOSTAddRemoveProductCartItems(addToCartModel);

        /*RequestDeleteProductCartItem deleteProductCartItem = new RequestDeleteProductCartItem(cartId, orgId);
        Call<ResponseAddToCartItems> call = apiInterface.apiPOSTDeleteCartItem(deleteProductCartItem);*/

        call.enqueue(new Callback<ResponseAddToCartItems>() {
            @Override
            public void onResponse(@NonNull Call<ResponseAddToCartItems> call, @NonNull Response<ResponseAddToCartItems> response) {
                assert response.body() != null;
                Log.e("OrderProducts-->", "" + response.body().getResult());
                if (response.body().getResult() > 0) {
                    showToast("Product item removed from cart.");
                    apiCallGetCartItems();
                } else {
                    showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseAddToCartItems> call, @NonNull Throwable t) {
                Log.e(" onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }

    /** ----------------- API Implementation --> After Buy Now is done. then call Order Products and navigate to ItemsInCart --------------------------- */
   /* private void apiOrderProducts(int cartId) {
        showPDialog(getString(R.string.loading));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        RequestBuyNow requestBuyNow = new RequestBuyNow(cartId);
        Call<ResponseOrderProducts> call = apiInterface.apiPOSTOrderProducts(requestBuyNow);
        call.enqueue(new Callback<ResponseOrderProducts>() {
            @Override
            public void onResponse(@NonNull Call<ResponseOrderProducts> call, @NonNull Response<ResponseOrderProducts> response) {
                hidePDialog();
                assert response.body() != null;
                Log.e("OrderProducts-->", "" + response.body().getOrderId());
                if (response.body().getOrderId() > 0) {
                    showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
                    startActivity(new Intent(mContext, BasketActivity.class)
                            .putExtra("type", "ProductsItemsInCart"));
                } else {
                    showToast((response.body().getMessage().isEmpty()) ? "" : response.body().getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseOrderProducts> call,@NonNull Throwable t) {
                hidePDialog();
                Log.e(" onFailure-->", "" + t.getLocalizedMessage());
            }
        });
    }*/
}