package com.mstech.gamesnatcherz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils.showProgress
import com.mstech.gamesnatcherz.adapter.RedeemPrizesAdapter
import com.mstech.gamesnatcherz.model.RedeemPrizeList
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.launch
import org.json.JSONObject

class GalleryFragment : Fragment() {

 lateinit var root :View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        root.recyclerView?.layoutManager = linearLayoutManager


        return root
    }

    override fun onResume() {
        super.onResume()
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("No Internet Connection")
        } else {
            lifecycleScope.launch {
                getRedeemPrizes()
            }
        }
    }
    private suspend fun getRedeemPrizes() {
        showProgress(requireContext(), true)
        val response = RetrofitApi().getredeemPrizes(SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"),"0")
                try {
                     if(response.isSuccessful){
                         var mainlist = mutableListOf<RedeemPrizeList>()
                         var mainobj = JSONObject(response.body()?.string())
                         var swipelist = mainobj.getJSONArray("Swipeandwin")
                         for (i in 0 until swipelist.length()){
                             var obj = swipelist.getJSONObject(i)
                             var model:RedeemPrizeList = RedeemPrizeList(obj.getString("GameConditions")
                                 ,obj.getString("PrizeImagePath"),obj.getString("PrizeText"),obj.getString("RedeemCode")
                                 ,obj.getInt("ResultId"),obj.getInt("Shared"),obj.getString("SharedFrom"),obj.getInt("Type"))

                             mainlist.add(model)
                         }
                         var quizlist = mainobj.getJSONArray("Quiz")
                         for (i in 0 until quizlist.length()){
                             var obj = quizlist.getJSONObject(i)
                             var model:RedeemPrizeList = RedeemPrizeList(obj.getString("BusinessName")
                                 ,obj.getString("PrizeImagePath"),obj.getString("PrizeText"),obj.getString("RedeemCode")
                                 ,obj.getInt("ResultId"),obj.getInt("Shared"),obj.getString("SharedFrom"),obj.getInt("Type"))

                             mainlist.add(model)
                         }
                         var smartquizlist = mainobj.getJSONArray("SmartQuiz")
                         for (i in 0 until smartquizlist.length()){
                             var obj = smartquizlist.getJSONObject(i)
                             var model:RedeemPrizeList = RedeemPrizeList(obj.getString("BusinessName")
                                 ,obj.getString("PrizeImagePath"),obj.getString("PrizeText"),obj.getString("RedeemCode")
                                 ,obj.getInt("ResultId"),obj.getInt("Shared"),obj.getString("SharedFrom"),obj.getInt("Type"))

                             mainlist.add(model)
                         }
                         var surveyquizlist = mainobj.getJSONArray("Survey")
                         for (i in 0 until surveyquizlist.length()){
                             var obj = surveyquizlist.getJSONObject(i)
                             var model:RedeemPrizeList = RedeemPrizeList(obj.getString("BusinessName")
                                 ,obj.getString("PrizeImagePath"),obj.getString("PrizeText"),obj.getString("RedeemCode")
                                 ,obj.getInt("ResultId"),obj.getInt("Shared"),obj.getString("SharedFrom"),obj.getInt("Type"))

                             mainlist.add(model)
                         }
                         var adapter: RedeemPrizesAdapter? =
                             mainlist.let {
                                 it.let { it1 ->
                                     RedeemPrizesAdapter(
                                         requireContext(),
                                         it1
                                     )
                                 }
                             }
                         root.recyclerView?.adapter = adapter
                         adapter?.notifyDataSetChanged()
                     }


                } catch (e: Exception) {
                    e.printStackTrace() }
        showProgress(requireContext(), false)
    }
}