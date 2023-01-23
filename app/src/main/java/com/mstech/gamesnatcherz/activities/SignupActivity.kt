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
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
//        val agelist = arrayOf("Select Age Range","10-18", "19-40","40-65","65+")
//        val genderlist = arrayOf("Select Gender","Male","Female","Other")
        val countrylist = arrayOf("India", "Australia")
        val ageadapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, countrylist)
        spinner1.adapter = ageadapter
//        val genderadapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, genderlist)
//        gender_spinner.adapter = genderadapter
        signup_btn.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(fname.text)) ToastUtils.showShort("Enter First Name")
            else if (!(email.text?.trim().isValidEmail())) ToastUtils.showShort("Enter Email-Id")
            else if (TextUtils.isEmpty(mobileno.text)) ToastUtils.showShort("Enter Mobile Number")
            else if ((password.text)?.length!! < 4) ToastUtils.showShort("Enter 4 Digit Pin")
            else if (password.text.toString() != confirm_pin.text.toString()) ToastUtils.showShort("Confirm pin not same")
//            else if (TextUtils.isEmpty(zipcode.text))
//                ToastUtils.showShort("Enter Zip Code")
//            else if (TextUtils.isEmpty(address.text))
//                ToastUtils.showShort("Enter Address")
            else {
                lifecycleScope.launch {
                    signup()
                }
            }

        })
        have_account.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        })
    }

    private fun CharSequence?.isValidEmail() =
        !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private suspend fun signup() {
        MyUtils.showProgress(this, true)
        var body = JSONObject()
        body.put("FirstName", fname.text)
        body.put("LastName", lname.text)
        body.put("Mobile", mobileno.text)
        body.put("Email", email.text)
        body.put("Pin", password.text)
        body.put("Age", "")
        body.put("Gender", "")
        body.put("Pin", password.text)
        body.put("ZipCode", "")
        body.put("Address", spinner1.selectedItemPosition)
        body.put("BusinessId", "1")
        var finalbody =
            ((body)).toString().toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())

        val response = RetrofitApi().getSignUpMethod(finalbody)
        if (response.isSuccessful) {

            val res = response.body()!!
            if (res.status == true) {
                if (res.data?.id == -1 || res.data?.id == -2) {
                    ToastUtils.showShort(res.data.message!!.toString())
                } else {
                    ToastUtils.showShort("Signup Successfully")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            } else {
                ToastUtils.showShort("Server Error")
            }

        } else {
            ToastUtils.showShort(response.errorBody().toString())
        }
        MyUtils.showProgress(this, false)
    }
}