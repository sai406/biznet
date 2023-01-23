package com.mstech.gamesnatcherz.utils

import com.mstech.gamesnatcherz.BuildConfig
import com.mstech.gamesnatcherz.ReceiptsRestaurentResponse
import com.mstech.gamesnatcherz.model.*
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitApi {

    @GET("api/CheckCustomerLogin")
    suspend fun getLoginMethod(
        @Query("mob") mobilenumber: String,
        @Query("pin") pin: String,
        @Query("deviceid") did: String,
        @Query("apptype") type: String
    ): Response<LoginResponse>

    @GET("api/GetSwipeGamePlaydetails")
    suspend fun getPrizebyCid(
        @Query("id") id: String,
        @Query("Cid") cid: String
    ): Response<PrizeResponse>

    @GET("api/GetPrizeDetails")
    suspend fun getprizeOptions(
        @Query("Pzid") prizeid: String,
        @Query("GameId") gameid: String
    ): Response<PrizeOptionsResponse>

    @GET("api/RedeemPrize")
    suspend fun redeemPrize(
        @Query("cid") cid: String,
        @Query("gid") gameid: String,
        @Query("pznum") prizenum: String,
        @Query("Promocheck") promo: String
    ): Response<RedeemResponse>

//    @GET("api/GetBusinessById")
//    suspend fun getBusinessbyid(@Query("Id")id :String) : Response<OutletResponse>

    @POST("api/RegisterCustomer")
    suspend fun getSignUpMethod(@Body postdata: RequestBody): Response<SignUpResponse>

    @POST("api/SendBill")
    suspend fun sendBill(@Body postdata: RequestBody): Response<ResponseBody>

    @GET("api/GetAvailableGames")
    suspend fun getAvailableGames(
        @Query("bid") bid: String,
        @Query("cid") cid: String
    ): Response<GamesListResponse>
// @GET("api/GetGameResultsByCustomer")
//    suspend fun getredeemPrizes(@Query("Cid")cid:String):Response<RedeemPrizeResponse>

    @GET("api/GetCustomerPrizes")
    suspend fun getredeemPrizes(
        @Query("Cid") cid: String,
        @Query("bid") bid: String
    ): Response<ResponseBody>

    @GET("api/GetSwipeandWinClaimedBusiness")
    suspend fun getRestaurentHistory(@Query("cid") cid: String): Response<List<RestaurentHistoryResponse>>

    // @POST("api/admingetbusiness")
//    suspend fun getPartners(@Body postdata: RequestBody):Response<List<RestaurentHistoryResponse>>
    @GET("api/GetBusinessByLocation")
    suspend fun getPartners(
        @Query("AdminId") id: String,
        @Query("Latitude") latitude: Double,
        @Query("Longitude") longitude: Double,
        @Query("Radius") radius: String,
        @Query("cid") cid: String
    ): Response<List<RestaurentHistoryResponse>>

    @GET("api/GetFavouriteBusiness")
    suspend fun getFavorites(@Query("cid") cid: String): Response<List<RestaurentHistoryResponse>>

    @GET("api/GetCustomerProfile")
    suspend fun getProfileInfo(@Query("id") cid: String): Response<ProfileResponse>

    @GET("api/CustomerRecentlyVisitedBusiness")
    suspend fun getRecentVisits(@Query("cid") cid: String): Response<ReceiptsRestaurentResponse>

    @GET("api/GetSwipeandWinBusiness")
    suspend fun getSwipeandWin(@Query("cid") cid: String): Response<List<RestaurentHistoryResponse>>

    @GET("api/GetSurveyBusiness")
    suspend fun getSurvey(@Query("cid") cid: String): Response<List<RestaurentHistoryResponse>>

    @GET("api/GetQuizBusiness")
    suspend fun getTextQuiz(@Query("cid") cid: String): Response<List<RestaurentHistoryResponse>>

    @GET("api/GetSmartQuizBusiness")
    suspend fun getSmartQuiz(@Query("cid") cid: String): Response<List<RestaurentHistoryResponse>>

    @GET("api/GetAllCustomers")
    suspend fun getCustomerList(@Query("Cid") cid: String): Response<CustomerListResponse>

    @GET("api/ForgotPassword")
    suspend fun getForgotPin(@Query("email") emailid: String): Response<ForgotPinResponse>

    @GET("api/GetNotificationbyCustomer")
    suspend fun getNotifications(
        @Query("cid") cid: String,
        @Query("bid") bid: String
    ): Response<List<NotificationResponse>>

    companion object {

        operator fun invoke(): RetrofitApi {

            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            } else {
                interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.NONE }
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://apmmarketing.co.nz/")
                .client(client)
                .build()
                .create(RetrofitApi::class.java)
        }

    }
}