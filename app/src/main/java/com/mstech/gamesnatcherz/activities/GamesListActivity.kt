package com.mstech.gamesnatcherz.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils.showProgress
import com.mstech.gamesnatcherz.adapter.GamesAdapter
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_games_list.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.coroutines.launch

class GamesListActivity : AppCompatActivity() {
    var businessid: String = "0"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games_list)
        supportActionBar?.hide()

        ivBack.setOnClickListener {
            onBackPressed()
        }
        tvHeader.text = " Swipe and Win"
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager
        if (intent.extras != null) {
            businessid = intent.extras?.getString("data", "0").toString()
        }

        Log.d("TAG", "onCreate: $businessid")
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("No Internet Connection")
        } else {
            lifecycleScope.launch {
                getAvailableGames()
            }
        }
    }


    private suspend fun getAvailableGames() {
        showProgress(this, true)
        val response = RetrofitApi().getAvailableGames(
            businessid,
            SPStaticUtils.getString(SharedKey.CUSTOMER_ID)
        )
        if (response.isSuccessful) {
            if (response.body()?.success!!) {
                try {

                    var devicelist = response.body()?.data
                    LogUtils.e(response.body())
                    var adapter: GamesAdapter? =
                        devicelist.let {
                            it?.let { it1 ->
                                GamesAdapter(
                                    this,
                                    it1
                                )
                            }
                        }
                    recyclerView?.adapter = adapter
                    adapter?.notifyDataSetChanged()
                    if (devicelist!!.isNotEmpty()) {
                    } else {
                        ToastUtils.showShort("No Games  Found")
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                ToastUtils.showShort(response.errorBody().toString())
            }

        }
        showProgress(this, false)
    }
}