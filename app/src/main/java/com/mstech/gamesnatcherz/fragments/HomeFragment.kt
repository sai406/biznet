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
import com.mstech.gamesnatcherz.utils.MyUtils
import com.mstech.gamesnatcherz.utils.RetrofitApi
import com.mstech.gamesnatcherz.utils.SharePref
import com.smarteist.autoimageslider.SliderView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

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
                val adapter = WeekSpecialAdapter(requireActivity(), response.body()?.take(5))
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
