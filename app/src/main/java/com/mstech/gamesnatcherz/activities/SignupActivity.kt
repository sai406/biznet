package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import org.json.JSONObject


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
        val agelist = arrayOf("Select Age Range","10-18", "19-40","40-65","65+")
        val genderlist = arrayOf("Select Gender","Male","Female","Other")
        val ageadapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, agelist)
        spinner1.adapter = ageadapter
        val genderadapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderlist)
        gender_spinner.adapter = genderadapter
        signup_btn.setOnClickListener( View.OnClickListener {
            if (TextUtils.isEmpty(fname.text))
                ToastUtils.showShort("Enter First Name")
            else if (!(email.text.isValidEmail()))
                ToastUtils.showShort("Enter Email-Id")
           else if (TextUtils.isEmpty(mobileno.text))
                ToastUtils.showShort("Enter Mobile Number")
            else if ((password.text)?.length!!<4)
                ToastUtils.showShort("Enter 4 Digit Pin")
//            else if(spinner1.selectedItemPosition==0)
//                ToastUtils.showShort("Select Age")
           else if(gender_spinner.selectedItemPosition==0)
                ToastUtils.showShort("Select Gender")
            else if (TextUtils.isEmpty(zipcode.text))
                ToastUtils.showShort("Enter Zip Code")
//            else if (TextUtils.isEmpty(address.text))
//                ToastUtils.showShort("Enter Address")
            else{
                lifecycleScope.launch {
                    signup()
                }
            }

        })
        have_account.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        })
    }
    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
   private suspend fun signup() {
       MyUtils.showProgress(this, true)
       var body = JSONObject()
       body.put("FirstName",fname.text)
       body.put("LastName",lname.text)
       body.put("Mobile",mobileno.text)
       body.put("Email",email.text)
       body.put("Pin",password.text)
       body.put("Age",spinner1.selectedItemPosition)
       body.put("Gender",gender_spinner.selectedItemPosition)
       body.put("Pin",password.text)
       body.put("ZipCode",zipcode.text)
       body.put("Address",address.text)
       body.put("BusinessId","1")
       var finalbody = RequestBody.create(
           okhttp3.MediaType.parse("application/json; charset=utf-8"),
           ((body)).toString()
       )

       val response = RetrofitApi().getSignUpMethod(finalbody)
       if (response.isSuccessful) {

           var res = response.body()!!
           if(res.status==true){
               if (res.data?.id==-1 || res.data?.id==-2){
                   ToastUtils.showShort(res.data!!.message!!.toString())
               }else{
                   startActivity(Intent(this,LoginActivity::class.java))
                   finish()
                   ToastUtils.showShort("Signup Successfull")
               }
           }else{
               ToastUtils.showShort("Server Error")
           }

       }else{
           ToastUtils.showShort(response.errorBody().toString())
       }
       MyUtils.showProgress(this, false)
   }
}