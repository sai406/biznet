package com.mstech.gamesnatcherz.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.BusinessItem
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.RecentlyVisitedItem
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.adapter.AllBusinessAdapter
import com.mstech.gamesnatcherz.adapter.RecentVisitAdapter
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_receipts_restaurent.*
import kotlinx.coroutines.launch

class ReceiptsRestaurentActivity : AppCompatActivity() {
    var adapte: AllBusinessAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipts_restaurent)
        supportActionBar?.hide()
        val linearLayoutManager = GridLayoutManager(this, 3)
        recyclerView?.layoutManager = linearLayoutManager
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recentrecycler?.layoutManager = layoutManager

        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("No Internet Connection")
        } else {
            lifecycleScope.launch {
                getRestaurentHistory()
            }
        }
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapte?.filter?.filter(newText)
                return false
            }

        })
        val searchIcon = search.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)
        val cancelIcon = search.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)
        val textView = search.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
    }

    private suspend fun getRestaurentHistory() {
        MyUtils.showProgress(this, true)
        val response =
            RetrofitApi().getRecentVisits(SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0"))
        try {
            if (response.isSuccessful) {
                var devicelist = response.body()?.business
                var businesslist = response.body()?.recentlyVisited
                adapte =
                    devicelist.let {
                        it?.let { it1 ->
                            AllBusinessAdapter(
                                this,
                                it1 as List<BusinessItem>
                            )
                        }
                    }
                recyclerView?.adapter = adapte
                adapte?.notifyDataSetChanged()
                var recentadapter: RecentVisitAdapter? =
                    businesslist.let {
                        it?.let { it1 ->
                            RecentVisitAdapter(
                                this,
                                it1 as List<RecentlyVisitedItem>
                            )
                        }
                    }
                recyclerView?.adapter = adapte
                recentrecycler?.adapter = recentadapter
                adapte?.notifyDataSetChanged()
                recentadapter?.notifyDataSetChanged()
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
        MyUtils.showProgress(this, false)
    }


}