package com.mstech.gamesnatcherz.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.model.ProfileResponse
import kotlinx.android.synthetic.main.activity_signup.*

class UpdateProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        var bundle : Bundle ? = intent.extras
        bundle?.apply {
            //Parcelable Data
            val profileResponse: ProfileResponse? = getParcelable("profile")
            if (profileResponse != null) {
                mobileno.setText(profileResponse.data?.mobile)
            }
        }
    }
}