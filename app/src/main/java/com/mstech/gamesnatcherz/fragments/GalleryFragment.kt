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
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import kotlinx.coroutines.launch

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
        val response = RetrofitApi().getredeemPrizes(SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"))
                try {
                     if(response.isSuccessful){
                         var devicelist = response.body()?.data
                         var adapter: RedeemPrizesAdapter? =
                             devicelist.let {
                                 it?.let { it1 ->
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