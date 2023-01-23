package com.mstech.gamesnatcherz.retro;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** HARISH GADDAM
 *  Created on 29/01/2021
 * */

public class ApiClient {

    public static final String BASE_URL = "https://apmmarketing.co.nz/api/"; //live
    private static Retrofit retrofit = null;

    static {
        HttpsURLConnection.setDefaultSSLSocketFactory(new NoSSLv3Factory());
    }

    //============== Retrofit Initiation ================================
    public static Retrofit getClient() {

        HttpLoggingInterceptor logLevel = new HttpLoggingInterceptor();
        logLevel.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logLevel);  // <-- this is the important line

        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()  // added recently ,can be removed
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }

    final class SubUrls {
            static final String POST_GET_PRODUCTS = BASE_URL + "GetProducts?bid=1&pgsize=-1&pgindex=1&str=";
        static final String GET_PRODUCTS_DETAILS = BASE_URL + "getproductdetails";
        static final String GET_PRODUCTS_REVIEWS = BASE_URL + "GetProductReviews";
        static final String GET_PRODUCTS_RATINGS = BASE_URL + "GetProductRating";
        static final String GET_PRODUCTS_MEMBER_RATINGS = BASE_URL + "GetMemberProductRating";
        static final String GET_PRODUCTS_SIZES = BASE_URL + "GetProductAttributes";
        static final String POST_ADD_PRODUCT_RATING = BASE_URL + "AddProductRating";
        static final String POST_ADD_PRODUCT_REVIEW = BASE_URL + "AddProductReview";
        static final String POST_ADD_REMOVE_PRODUCT_CART_ITEMS = BASE_URL + "AddRemoveProductCartItems";
        static final String POST_GET_PRODUCT_ITEMS_IN_CART = BASE_URL + "Getproductcart";
        static final String POST_ORDER_PRODUCTS = BASE_URL + "OrderProducts";
        static final String POST_DELETE_CART_ITEM = BASE_URL + "RemoveProductCart";
        static final String GET_ORDER_PRODUCTS = BASE_URL + "GetProductOrders";

    }
}




