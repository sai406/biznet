package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils.showProgress
import com.mstech.gamesnatcherz.product.activity.ItemsInCartActivity
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.title="Profile"
        supportActionBar?.hide()
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("No Internet Connection")
        } else {
            lifecycleScope.launch {
                getCustomerDetails()
            }
        }
        ivBack.setOnClickListener {
            onBackPressed()
        }
        tvHeader.text = "Profile"
        cart.setOnClickListener {
            startActivity(Intent(this, ItemsInCartActivity::class.java))
        }
        logout.setOnClickListener {
            SPStaticUtils.clear()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        orders.setOnClickListener {
            startActivity(Intent(this, BasketActivity::class.java))
        }

    }
    private suspend fun getCustomerDetails() {
        showProgress(this, true)
        var response = RetrofitApi().getProfileInfo(SPStaticUtils.getString(SharedKey.CUSTOMER_ID))
        if (response.isSuccessful) {
            name.text = response.body()?.data?.firstName+response.body()?.data?.lastName
            email.text = response.body()?.data?.email
            contact.text = response.body()?.data?.mobile
//            age.text ="Age : "+ response.body()?.data?.agestring
//            gender.text = response.body()?.data?.genderstring
//            address.text = response.body()?.data?.address.toString()+", "+ response.body()?.data?.zipCode.toString()
        } else {
            ToastUtils.showShort(response.errorBody().toString())
        }
        showProgress(this, false)
    }
}