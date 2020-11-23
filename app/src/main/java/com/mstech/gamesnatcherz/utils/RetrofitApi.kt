package com.mstech.gamesnatcherz.utils

import com.mstech.gamesnatcherz.model.*
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitApi {

    @GET("api/CheckCustomerLogin")
    suspend fun getLoginMethod(@Query("mob")mobilenumber :String,@Query("pin") pin :String,@Query("deviceid") did:String,@Query("apptype")type:String) : Response<LoginResponse>
    @GET("api/GetSwipeGamePlaydetails")
    suspend fun getPrizebyCid(@Query("id")id :String,@Query("Cid") cid :String) : Response<PrizeResponse>

    @GET("api/GetPrizeDetails")
    suspend fun getprizeOptions(@Query("Pzid")prizeid :String,@Query("GameId") gameid :String) : Response<PrizeOptionsResponse>
@GET("api/RedeemPrize")
    suspend fun redeemPrize(@Query("cid")cid :String,@Query("gid") gameid :String,@Query("pznum") prizenum :String,@Query("Promocheck")promo:String) : Response<RedeemResponse>

//    @GET("api/GetBusinessById")
//    suspend fun getBusinessbyid(@Query("Id")id :String) : Response<OutletResponse>

    @POST("api/RegisterCustomer")
    suspend fun getSignUpMethod(@Body postdata : RequestBody) : Response<SignUpResponse>

    @GET("api/GetAvailableGames")
    suspend fun getAvailableGames(@Query("bid")bid:String,@Query("cid")cid:String): Response<GamesListResponse>
// @GET("api/GetGameResultsByCustomer")
//    suspend fun getredeemPrizes(@Query("Cid")cid:String):Response<RedeemPrizeResponse>

    @GET("api/GetCustomerPrizes")
    suspend fun getredeemPrizes(@Query("Cid")cid:String,@Query("bid") bid:String):Response<ResponseBody>
 @GET("api/GetSwipeandWinClaimedBusiness")
    suspend fun getRestaurentHistory(@Query("cid")cid:String):Response<List<RestaurentHistoryResponse>>
// @POST("api/admingetbusiness")
//    suspend fun getPartners(@Body postdata: RequestBody):Response<List<RestaurentHistoryResponse>>
    @GET("api/GetBusinessByLocation")
    suspend fun getPartners(@Query("AdminId")id:String,@Query("Latitude") latitude:Double ,@Query("Longitude") longitude:Double,@Query("Radius") radius:String,@Query("cid") cid:String):Response<List<RestaurentHistoryResponse>>
 @GET("api/GetFavouriteBusiness")
    suspend fun getFavorites(@Query("cid")cid:String):Response<List<RestaurentHistoryResponse>>
 @GET("api/GetCustomerProfile")
    suspend fun getProfileInfo(@Query("id")cid:String):Response<ProfileResponse>

    @GET("api/GetSwipeandWinBusiness")
    suspend fun getSwipeandWin(@Query("cid")cid:String):Response<List<RestaurentHistoryResponse>>

 @GET("api/GetSurveyBusiness")
    suspend fun getSurvey(@Query("cid")cid:String):Response<List<RestaurentHistoryResponse>>

    @GET("api/GetQuizBusiness")
    suspend fun getTextQuiz(@Query("cid")cid:String):Response<List<RestaurentHistoryResponse>>
@GET("api/GetSmartQuizBusiness")
    suspend fun getSmartQuiz(@Query("cid")cid:String):Response<List<RestaurentHistoryResponse>>

    @GET("api/GetAllCustomers")
    suspend fun getCustomerList(@Query("Cid")cid:String):Response<CustomerListResponse>
 @GET("api/GetNotificationbyCustomer")
    suspend fun getNotifications(@Query("cid") cid:String,@Query("bid") bid :String):Response<List<NotificationResponse>>
    companion object{
        private val  isPrize :Boolean = false
      operator  fun invoke():RetrofitApi{
          return Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create())
              .baseUrl("http://www.gamesnatcherz.com/")
              .build()
              .create(RetrofitApi::class.java)
      }

    }
}