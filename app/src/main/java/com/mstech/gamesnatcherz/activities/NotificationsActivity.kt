package com.mstech.gamesnatcherz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.adapter.NotificationAdapter
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_games_list.*
import kotlinx.coroutines.launch

class NotificationsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)
        supportActionBar?.title=" Notifications"
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("No Internet Connection")
        } else {
            lifecycleScope.launch {
                getNotifications()
            }
        }
    }
    private suspend fun getNotifications() {
        MyUtils.showProgress(this, true)
        val response = RetrofitApi().getNotifications(SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"),"0")
        try {
            if(response.isSuccessful){
                var devicelist = response.body()
                var adapter: NotificationAdapter? =
                    devicelist.let {
                        it?.let { it1 ->
                            NotificationAdapter(
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