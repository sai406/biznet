package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
     lateinit var token: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        supportActionBar?.title = "Login"
        login_btn.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(mobileno.text)){
                ToastUtils.showShort("Enter Mobile Number")
            }else if ((password.text)?.length!!<4){
                ToastUtils.showShort("Enter 4 Digit Pin")
            }
            else {
                lifecycleScope.launch {
                    loginMethod(mobileno.text.toString(),password.text.toString())
                }
            }
        })
        no_account.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this , SignupActivity::class.java))
            finish()
        })
        skip.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,DashboardActivity::class.java))
            SPStaticUtils.put(SharedKey.ISLOGIN,"true")
            SPStaticUtils.put(SharedKey.CUSTOMER_ID, "0")
            SPStaticUtils.put(SharedKey.BUSINESS_ID,"1")
            finish()
        })
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                 token = task.result?.token.toString()

                // Log and toast
                Log.d("TAG", token)
            })

    }

    private suspend fun loginMethod(mobileno:String,password :String) {
        MyUtils.showProgress(this, true)
        val response = RetrofitApi().getLoginMethod(mobileno,password,token,"1")
             if (response.isSuccessful){
                 if (response.body()?.success!!){
                var  userdata = response.body()
                     SPStaticUtils.put(SharedKey.ISLOGIN,"true")
                     SPStaticUtils.put(SharedKey.CUSTOMER_ID, userdata?.data?.customerId.toString())
                     SPStaticUtils.put(SharedKey.BUSINESS_ID,userdata?.data?.businessId.toString())
                     SPStaticUtils.put(SharedKey.NAME,userdata?.data?.firstName+userdata?.data?.lastName)
                     SPStaticUtils.put(SharedKey.EMAIL,userdata?.data?.email)
                     SPStaticUtils.put(SharedKey.MOBILE,userdata?.data?.mobile)

                     if (SharedKey.ISPRIZE==true){
                         SharedKey.ISPRIZE == false
                         onBackPressed()
                     }else{
                         startActivity(Intent(this,DashboardActivity::class.java))
                         finish()
                     }

                 }else{
                     ToastUtils.showShort("Invalid Login")
                 }
             }else{
                 ToastUtils.showShort(response.errorBody().toString())
             }
        MyUtils.showProgress(this, false)

    }

}

