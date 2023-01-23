package com.mstech.gamesnatcherz.fragments

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.navigation.NavigationView
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.activities.AllGamesActivity
import com.mstech.gamesnatcherz.activities.GsPartnersActivity
import com.mstech.gamesnatcherz.activities.LoginActivity
import com.mstech.gamesnatcherz.activities.ProfileActivity
import com.mstech.gamesnatcherz.adapter.SliderAdapt
import com.mstech.gamesnatcherz.adapter.WeekSpecialAdapter
import com.mstech.gamesnatcherz.databinding.FragmentHomeBinding
import com.mstech.gamesnatcherz.model.CategoryRespons
import com.mstech.gamesnatcherz.model.Goodsmodel
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.model.products.RequestGetProducts
import com.mstech.gamesnatcherz.model.products.ResponseGETProduct
import com.mstech.gamesnatcherz.product.activity.ProductsActivity
import com.mstech.gamesnatcherz.retro.ApiClient
import com.mstech.gamesnatcherz.retro.ApiInterface
import com.mstech.gamesnatcherz.utils.RetrofitApi
import com.mstech.gamesnatcherz.utils.SharePref
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {


//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        root.promo.setOnClickListener(View.OnClickListener {
//           requireActivity().startActivity(Intent(activity, QrScannerActivity::class.java))
//        })
//        root.account.setOnClickListener(View.OnClickListener {
//            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
//                requireActivity().startActivity(Intent(activity, ProfileActivity::class.java))
//            }else{
//                ToastUtils.showShort("please login to play the game")
//            }
//
//        })
//        root.notification.setOnClickListener(View.OnClickListener {
//            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
//                requireActivity().startActivity(Intent(activity, NotificationsActivity::class.java))
//            }else{
//                ToastUtils.showShort("please login to play the game")
//            }
//
//        })
//        root.gspartner.setOnClickListener(View.OnClickListener {
//           requireActivity().startActivity(Intent(activity, GsPartnersActivity::class.java))
//        })


//        return root
//    }
//}

    private val PERMISSION_REQUEST_CODE = 200
    var doubleBackToExitPressedOnce = false
    var sharedPreferences: SharedPreferences? = null
    var basket: ImageView? = null
    var custid: String? = null
    var merch: String? = null
    var moodvalue: String? = null
    var res: String? = null
    var totaladdress: kotlin.String? = null
    var tokenid: String? = null
    var dialog: Dialog? = null
    var categories: SliderView? = null
    var offers_slider: SliderView? = null
    var catlist: MutableList<CategoryRespons> = ArrayList<CategoryRespons>()
    var adapter: SliderAdapt? = null
    var weekAdapter: WeekSpecialAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null
    var bottom: WebView? = null
    var navigationView: NavigationView? = null
    var cartCount: TextView? = null
    private val pDialog: ProgressDialog? = null
    private val EventList: MutableList<Goodsmodel> = ArrayList()

    private var _binding: FragmentHomeBinding? = null
    var sharePref: SharePref? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        sharePref = SharePref(requireActivity())
        binding.businessbtn.setOnClickListener(View.OnClickListener {
            requireActivity().startActivity(Intent(activity, GsPartnersActivity::class.java))
        })

        binding.promotionsbtn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(requireActivity(), ProductsActivity::class.java))
        })
        binding.profileBtn.setOnClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                requireActivity().startActivity(Intent(activity, ProfileActivity::class.java))
            } else {
                ToastUtils.showShort("please login to see the profile")
                requireActivity().startActivity(Intent(activity, LoginActivity::class.java))

            }
        }
        binding.digitalbtn.setOnClickListener {
            startActivity(
                Intent(
                    requireActivity(),
                    AllGamesActivity::class.java
                ).putExtra("businessid", "0")
            )
        }
        lifecycleScope.launch {
            getRestaurentHistory()
            apiProductList()
        }


//        goodsofferlist()


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun apiProductList() {
        val apiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        val products = RequestGetProducts(
            "",
            -1,
            1
        )
        val call = apiInterface.apiPOSTGetProducts(products)
        call.enqueue(object : Callback<java.util.ArrayList<ResponseGETProduct?>?> {
            override fun onResponse(
                call: Call<java.util.ArrayList<ResponseGETProduct?>?>,
                response: Response<java.util.ArrayList<ResponseGETProduct?>?>
            ) {
                Log.e("GetProducts-->", "" + response.code())
//                arrayListProducts.clear()
//                if (isApiSuccess(response.code())) {
//                    assert(response.body() != null)
//                    if (response.body()!!.size > 0) {
//                        arrayListProducts.addAll(response.body())
//                        mAdapter = ProductsAdapter(mContext, arrayListProducts)
//                        rvProducts.setAdapter(mAdapter)
//                        mAdapter.notifyDataSetChanged()
//                    }
//                }

                val adapter = WeekSpecialAdapter(requireActivity(), response.body())
                binding.offersSlider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                binding.offersSlider.setSliderAdapter(adapter)
                binding.offersSlider.scrollTimeInSec = 3
                binding.offersSlider.isAutoCycle = true
                binding.offersSlider.startAutoCycle()
            }

            override fun onFailure(
                call: Call<java.util.ArrayList<ResponseGETProduct?>?>,
                t: Throwable
            ) {
                Log.e("onFailure-->", "" + t.localizedMessage)
            }
        })
    }


//    private fun goodsofferlist() {
//        EventList.clear()
//        val requestQueue: RequestQueue = Volley.newRequestQueue(requireActivity())
//        val url =
//            "https://www.gmilink.com/d/appservices/appweeklyspecialdeals.aspx?lon=0.0&dc=0&mi=2654&lat=0.0&cid=" + sharePref?.memberID
//        Log.d("productURL: ", "url:$url")
//        val movieReq = JsonArrayRequest(url,
//            { response ->
//                // show list if not null
//                if (!response.isNull(0) && response.length() > 0) {
//                    var i = 0
//                    while (i < response?.length()!! && i < 6) {
//                        try {
//                            val obj = response.getJSONObject(i)
//                            val Event = Goodsmodel()
//                            Event.dealname = obj.getString("DealName")
//                            Event.price = obj.getString("TotalPrice")
//                            Event.formatPrice = obj.getString("BuyPriceNoFormat")
//                            Event.dealprice = obj.getString("DealPrice")
//                            Event.dealimag = obj.getString("DealImage")
//                            Event.merchantimage = obj.getString("MerchantImg")
//                            Event.dealId = obj.getString("DealId")
//                            Event.discription = obj.getString("DealDescription")
//                            Event.merchantId = obj.getString("MerchantId")
//                            Event.bussinessaddress = obj.getString("Address")
//                            Event.bussinessName = obj.getString("MerchantName")
//                            Event.dealType = obj.getString("DealType")
//                            Event.url = obj.getString("website")
//                            Event.lat = obj.getString("Latitude")
//                            Event.lng = obj.getString("Longitude")
//                            Event.isFavourite = obj.getString("IsFavourite")
//                            Event.attributename = obj.getString("AttributeValues")
//                            //                                "AttributeValues" -> "[{"DealId":2923,"AttributeId":31,"AttributeName":"Kids Sizes","AttributeValues":[{"DealAttributeValueId":195,"AttributeValue":"1"},{"DealAttributeValueId":195,"AttributeValue":"2"},{"DealAttributeValueId":195,"AttributeValue":"4"},{"DealAttributeValueId":195,"AttributeValue":"6"},{"DealAttributeValueId":195,"AttributeValue":"8"},{"DealAttributeValueId":195,"AttributeValue":"10"},{"DealAttributeValueId":195,"AttributeValue":"12"},{"DealAttributeValueId":195,"AttributeValue":"14"},{"DealAttributeValueId":195,"AttributeValue":"16"}]}]"
//                            Event.condition = obj.getString("Conditions")
//                            Event.destinationUrl = obj.getString("DestinationUrl")
//                            Event.promotionalImages =
//                                obj.getJSONArray("PromotionalImages").toString()
//                            Event.digitalcoins = obj.getString("QoinAmountNoFormat")
//                            Event.sellingDealPrice = obj.getDouble("SellingDealPriceNoFormat")
//                            Event.buyPrice = obj.getString("BuyPrice")
//                            //                                    Event.setQoinMerchant(obj.getBoolean("IsQoinMerchant"));
//                            EventList.add(Event)
//                        } catch (e: JSONException) {
//                            e.printStackTrace()
//                        }
//                        i++
//                    }
//                    val adapter = WeekSpecialAdapter(requireActivity(), EventList)
//                    offers_slider!!.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
//                    offers_slider!!.setSliderAdapter(adapter)
//                    offers_slider!!.scrollTimeInSec = 3
//                    offers_slider!!.isAutoCycle = true
//                    offers_slider!!.startAutoCycle()
//                }
//            }, { error -> error.printStackTrace() })
//        requestQueue.add(movieReq)
//    }

    //    fun goodslist() {
//        catlist.clear()
//        val pDialog = ProgressDialog(requireActivity())
//        pDialog.setMessage("please wait.")
//        pDialog.setCancelable(false)
//        pDialog.show()
//        val requestQueue: RequestQueue = Volley.newRequestQueue(requireActivity())
//        val url = "https://www.gmilink.com/d/AppServices/appcategories.aspx?mi=2654&source=home"
//        Log.d("sss", "url:$url")
//        val movieReq = JsonArrayRequest(url,
//            { response ->
//                for (i in 0 until response.length()) {
//                    try {
//                        val obj = response.getJSONObject(i)
//                        val Event = CategoryRespons()
//                        Event.dealCatId = obj.getString("DealCatId")
//                        Event.categoryImage = obj.getString("CategoryImage")
//                        Event.parentDealCatId = obj.getString("CategoryImage")
//                        Event.dealCatName = obj.getString("DealCatName")
//                        catlist.add(Event)
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                }
//                val adapter = SliderAdapt(requireActivity(), catlist)
//                binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
//                binding.slider.setSliderAdapter(adapter)
//                binding.slider.scrollTimeInSec = 3
//                binding.slider.isAutoCycle = true
//                binding.slider.startAutoCycle()
//                pDialog.dismiss()
//            }, { error -> error.printStackTrace() })
//        requestQueue.add(movieReq)
//    }
    private suspend fun getRestaurentHistory() {
        try {
            MyUtils.showProgress(requireContext(), true)
            val response = RetrofitApi().getPartners(
                "1", 0.0, 0.0, "0",
                SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0")
            )

            try {
                if (response.isSuccessful) {
                    var devicelist = response.body()

                    val adapter = SliderAdapt(requireActivity(), devicelist)
                    binding.slider.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
                    binding.slider.setSliderAdapter(adapter)
                    binding.slider.scrollTimeInSec = 3
                    binding.slider.isAutoCycle = true
                    binding.slider.startAutoCycle()

                    Log.d("TAG", "getRestaurentHistory: " + response.body().toString())
                } else {
                    LogUtils.e(response.errorBody()?.string())
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
            MyUtils.showProgress(requireActivity(), false)
        } catch (e: Exception) {
            e.printStackTrace()
            MyUtils.showProgress(requireActivity(), false)
        }

    }


    fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(requireActivity(), "Please click BACK again to exit", Toast.LENGTH_SHORT)
            .show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
