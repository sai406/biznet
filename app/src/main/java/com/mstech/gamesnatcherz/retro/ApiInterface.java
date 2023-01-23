package com.mstech.gamesnatcherz.retro;

/** HARISH GADDAM
 *  Created on 29/01/2021
 * */


import com.mstech.gamesnatcherz.model.Basketproducts;
import com.mstech.gamesnatcherz.model.products.RequestAddProductRating;
import com.mstech.gamesnatcherz.model.products.RequestAddProductReview;
import com.mstech.gamesnatcherz.model.products.RequestBuyNow;
import com.mstech.gamesnatcherz.model.products.RequestDeleteProductCartItem;
import com.mstech.gamesnatcherz.model.products.RequestGetProductReviews;
import com.mstech.gamesnatcherz.model.products.RequestGetProducts;
import com.mstech.gamesnatcherz.model.products.ResponseAddProductRating;
import com.mstech.gamesnatcherz.model.products.ResponseAddToCartItems;
import com.mstech.gamesnatcherz.model.products.ResponseGETProduct;
import com.mstech.gamesnatcherz.model.products.ResponseGetProductOverallRatings;
import com.mstech.gamesnatcherz.model.products.ResponseGetProductSize;
import com.mstech.gamesnatcherz.model.products.ResponseOrderProducts;
import com.mstech.gamesnatcherz.model.products.ResponseProductReviews;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.RequestAddToCartItems;
import com.mstech.gamesnatcherz.model.products.product_items_in_cart.ResponseGetCartItem;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {

//    @POST(ApiClient.SubUrls.LOGIN)
//    Call<ResponsePOSTLogin> apiPOSTLogin(@Body RequestLogin requestLoginPojo);
//
//    @POST(ApiClient.SubUrls.POST_GET_VIDEOS)
//    Call<ArrayList<ResponseVideos>> apiPOSTVideos(@Body RequestGETVideos requestGETVideos);
//
//    @POST(ApiClient.SubUrls.POST_GET_VIDEOS_REVIEWS)
//    Call<ArrayList<Review>> apiPOSTVideosReviews(@Body RequestGETVideosReviews requestGETVideosReviews);
//
//    @GET(ApiClient.SubUrls.GET_LIKE_UNLIKE)
//    Call<ResponseLike> apiGETLikeUnLike(
//            @Query("videoid") int videoid,
//            @Query("mid") int mid);
//
//    @GET(ApiClient.SubUrls.GET_EVENT_DETAILS)
//    Call<ResponseEventDetails> apiGETEventDetails(@Query("eid") int eid);

    @POST(ApiClient.SubUrls.POST_GET_PRODUCTS)
    Call<ArrayList<ResponseGETProduct>> apiPOSTGetProducts(@Body RequestGetProducts requestGetProducts);

    @POST(ApiClient.SubUrls.GET_PRODUCTS_REVIEWS)
    Call<ArrayList<ResponseProductReviews>> apiPOSTGetProductReviews(@Body ResponseBody productReviews);

    @GET(ApiClient.SubUrls.GET_PRODUCTS_RATINGS)
    Call<ResponseGetProductOverallRatings> apiPOSTGetProductOverallRatings(@Query("pid") int pid);

    @GET(ApiClient.SubUrls.GET_PRODUCTS_SIZES)
    Call<ArrayList<ResponseGetProductSize>> apiGETProductSizes(@Query("pid") int pid);

    @POST(ApiClient.SubUrls.POST_ADD_PRODUCT_RATING)
    Call<ResponseAddProductRating> apiPOSTAddProductRating(@Body RequestAddProductRating addProductRating);

    @POST(ApiClient.SubUrls.POST_ADD_PRODUCT_REVIEW)
    Call<ResponseAddProductRating> apiPOSTAddProductReview(@Body RequestAddProductReview addProductReview);

    @POST(ApiClient.SubUrls.POST_ADD_REMOVE_PRODUCT_CART_ITEMS)
    Call<ResponseAddToCartItems> apiPOSTAddRemoveProductCartItems(@Body RequestAddToCartItems addToCartItems);

    @GET(ApiClient.SubUrls.POST_GET_PRODUCT_ITEMS_IN_CART)
    Call<ArrayList<ResponseGetCartItem>> apiGETProductItemsInCart(@Query("cid") String mid, @Query("bid") String oid);

    @POST(ApiClient.SubUrls.POST_ORDER_PRODUCTS)
    Call<ResponseOrderProducts> apiPOSTOrderProducts(@Body RequestBuyNow requestBuyNow);

    @POST(ApiClient.SubUrls.POST_DELETE_CART_ITEM)
    Call<ResponseAddToCartItems> apiPOSTDeleteCartItem(@Body RequestDeleteProductCartItem deleteProductCartItem);

    @GET(ApiClient.SubUrls.GET_ORDER_PRODUCTS)
    Call<ArrayList<Basketproducts>> apiGETBasketProductOrders(@Query("cid") String mid, @Query("bid") String oid);

/*    @GET(ApiClient.SubUrls.GET_BASKET_LIST)
    Call<ArrayList<ResponseBasketRoot>> apiGETBasketItems(@Query("mid") int mid, @Query("oid") int oid);*/

//    @GET(ApiClient.SubUrls.GET_WORKSHOP_LIST)
//    Call<ArrayList<ResponseWorkshopList>> apiGETWorkshopList(@Query("oid") int oid);
// @GET(ApiClient.SubUrls.GET_WORKSHOP_QLIST)
//    Call<List<SurveyResponse>> apiGETQWorkshopList(@Query("cid") int cid, @Query("mid") int mid);
//
//    @GET(ApiClient.SubUrls.GET_MYWORKSHOP_LIST)
//    Call<ArrayList<ResponseWorkshopList>> apiGETMyWorkshopList(@Query("oid") int oid, @Query("mid") int mid);
//
//    @GET(ApiClient.SubUrls.GET_MYWORKSHOP_QLIST)
//    Call<ArrayList<SurveyResponse>> apiGETQMyWorkshopList(@Query("cid") int cid, @Query("mid") int mid);
//
//    @GET(ApiClient.SubUrls.GET_WORKSHOP_CHAPTER_LIST)
//    Call<ResponseWorkshopChapterList> apiGETWorkshopChapterList(@Query("sid") int sid);
//
//    @GET(ApiClient.SubUrls.GET_WORKSHOP_CHAPTER_LIST_DETAILS)
//    Call<ResponseWorkshopChapterDetails> apiGETWorkshopChapterListDetails(@Query("cid") int cid);
//
//    @POST(ApiClient.SubUrls.POST_WORKSHOP_ADD_ENQUIRY)
//    Call<ResponseBody> apiPOSTWorkshopAddEnquiry(@Body RequestAddEnquiry addEnquiry);
//
//    @POST(ApiClient.SubUrls.SUBMIT_QUESTIONS)
//    Call<ResponseBody> apiSubmitAnswers(@Body String addEnquiry);
//
//    @GET(ApiClient.SubUrls.GET_LIVE_STREAMING_VIDEOS)
//    Call<ArrayList<ResponseGETLiveStreamingVideoList>> apiGETLiveStreamingVideos(@Query("orgid") int orgid, @Query("mid") int mid);
//
//    @GET(ApiClient.SubUrls.GET_ORG_LIST)
//    Call<ArrayList<ResponseGETOrg>> apiGETOrg();
//
//    @GET(ApiClient.SubUrls.GET_ADD_VIDEO_REVIEW_RATING)
//    Call<ResponseAddReviewRating> apiGETAddVideoReviewRating(
//            @Query("reviewid") int reviewid,
//            @Query("videoid") int videoid,
//            @Query("mid") int mid,
//            @Query("review") String review,
//            @Query("rating") int rating,
//            @Query("action") int action
//    );
//
//    @POST(ApiClient.SubUrls.POST_ADD_VIDEO_CART_ITEM)
//    Call<ResponseVideoAddToCart> apiPOSTVideoAddToCart(@Body RequestVideoAddToCart requestVideoAddToCart);
//
//    @GET(ApiClient.SubUrls.GET_VIDEO_CART_ITEMS)
//    Call<ArrayList<ResponseGetVideoCartItem>> apiGETVideoCartItems(@Query("mid") int mid, @Query("oid") int oid);
//
//    @POST(ApiClient.SubUrls.POST_DELETE_VIDEO_CART_ITEMS)
//    Call<ResponseAddToCartItems> apiPOSTDeleteVideoCartItem(@Body RequestDeleteProductCartItem requestDeleteVideoCartItem);
//
//    @POST(ApiClient.SubUrls.POST_ORDER_VIDEO_ITEMS_IN_CART)
//    Call<ResponseOrderProducts> apiPOSTOrderVideoItemsInCart(@Body RequestBuyNow requestOrderVideoItemsInCart);
//
//    @GET(ApiClient.SubUrls.GET_BASKET_PRODUCT_ORDERS)
//    Call<ArrayList<ResponseBasketProductOrders>> apiGETBasketProductOrders(@Query("mid") int mid, @Query("oid") int oid);
//
//    @GET(ApiClient.SubUrls.GET_BASKET_VIDEOS_ORDERS)
//    Call<ArrayList<ResponseGetBasketVideoOrder>> apiGETBasketVideosOrders(@Query("mid") int mid, @Query("oid") int oid);
//
//    @GET(ApiClient.SubUrls.GET_BOOKED_EVENTS)
//    Call<ArrayList<ResponseBookedEvent>> apiGETBookedEvents(@Query("mid") int mid);
//
//    @GET(ApiClient.SubUrls.GET_UP_COMING_EVENT)
//    Call<ArrayList<ResponseUpComingEvent>> apiGETEvent(
//            @Query("pgindex") int pgindex,
//            @Query("pgsize") int pgsize,
//            @Query("sortby") int sortby,
//            @Query("str") String str
//    );
//
//    @POST(ApiClient.SubUrls.POST_PROCEED_BOOK_EVENT)
//    Call<ResponseBookEventProceed> apiPOSTProceedBookEvent(@Body RequestBookEventProceed requestBookEventProceed);
}




