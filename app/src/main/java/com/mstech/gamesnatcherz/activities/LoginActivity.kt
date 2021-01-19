package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
import kotlinx.android.synthetic.main.forgotpin_layout.view.*
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
      var token: String =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        supportActionBar?.title = "Login"
        login_btn.setOnClickListener(View.OnClickListener {
            if (!(mobileno.text).isValidEmail()){
                ToastUtils.showShort("Enter Email-Id")
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
        forgotpin.setOnClickListener(View.OnClickListener {
            var dialogBuilder =  AlertDialog.Builder(this).create();
            var inflater = this.getLayoutInflater();
            var dialogView = inflater?.inflate(R.layout.forgotpin_layout, null);



            dialogView.buttonCancel.setOnClickListener(View.OnClickListener {
                dialogBuilder.dismiss();
            })
           dialogView.buttonSubmit.setOnClickListener(View.OnClickListener {

               lifecycleScope.launch {
                   forgotMethod(dialogView.edt_comment.text.toString())
                   dialogBuilder.dismiss()
               }
           })
            dialogBuilder.setView(dialogView);
            dialogBuilder.show();
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
    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

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


    private suspend fun forgotMethod(emailid:String) {
        MyUtils.showProgress(this, true)
        val response = RetrofitApi().getForgotPin(emailid)
             if (response.isSuccessful) {
                ToastUtils.showShort(response.body()?.message)
             }else{
                 ToastUtils.showShort(response.errorBody().toString())
             }
        MyUtils.showProgress(this, false)

    }

}

