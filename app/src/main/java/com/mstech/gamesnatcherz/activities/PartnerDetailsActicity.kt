package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.model.RestaurentHistoryResponse
import com.mstech.gamesnatcherz.product.activity.ProductsActivity
import kotlinx.android.synthetic.main.activity_partner_details_acticity.*
import kotlinx.android.synthetic.main.layout_header.*
import kotlinx.coroutines.launch

class PartnerDetailsActicity : AppCompatActivity() {
    //    private lateinit var mMap: GoogleMap
    private var data: RestaurentHistoryResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partner_details_acticity)
        supportActionBar?.hide()
        data = intent.getParcelableExtra<RestaurentHistoryResponse?>("data")
        ivBack.setOnClickListener {
            onBackPressed()
        }
        tvHeader.text = "Partner Details"
        address.text = data?.address
        name.text = data?.businessName
        tv_email.text = data?.email
        tv_mobile.text = data?.mobile
        tv_name.text = data?.firstName + " " + data?.lastName
        Glide.with(this).load(data?.businessLogoPath)
            .into(imageView)
//        val mapFragment = supportFragmentManager
//            .findFragmentById(R.id.map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
        // go to specials on sale
        promotions.setOnClickListener {
            startActivity(
                Intent(
                    this@PartnerDetailsActicity,
                    ProductsActivity::class.java
                )
            )
        }
        btnSpecialsOnSale.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                startActivity(
                    Intent(
                        this@PartnerDetailsActicity,
                        AllGamesActivity::class.java
                    ).putExtra("businessid", data?.businessId.toString())
                )
            }
        })
    }


//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//
//        // Add a marker in Sydney and move the camera
//        if (!data?.latitude!!.equals("") && !data?.longitude!!.equals("")) {
//            val sydney = LatLng(data?.latitude!!.toDouble(), data?.longitude!!.toDouble())
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
//        }
//
//    }
}