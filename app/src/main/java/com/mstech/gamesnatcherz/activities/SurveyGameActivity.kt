package com.mstech.gamesnatcherz.activities

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.adapter.SurveyAdapter
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_gs_partners.*
import kotlinx.coroutines.launch

class SurveyGameActivity : AppCompatActivity() {
    var adapter: SurveyAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipeand_wingame)
        supportActionBar?.hide()
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager

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
                adapter?.filter?.filter(newText)
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
        val response = RetrofitApi().getSurvey(SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"))
        try {
            if(response.isSuccessful){
                var devicelist = response.body()
                var adapter: SurveyAdapter? =
                    devicelist.let {
                        it?.let { it1 ->
                            SurveyAdapter(
                                this,
                                it1
                            )
                        }
                    }
                recyclerView?.adapter = adapter
                adapter?.notifyDataSetChanged()
            }


        } catch (e: Exception) {
            e.printStackTrace() }
        MyUtils.showProgress(this, false)
    }
}