package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.model.RestaurentHistoryResponse
import kotlinx.android.synthetic.main.activity_partner_details_acticity.*
import kotlinx.coroutines.launch

class PartnerDetailsActicity : AppCompatActivity(),OnMapReadyCallback {
    private lateinit var mMap: GoogleMap
    private var data: RestaurentHistoryResponse? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_partner_details_acticity)
        data = intent.getParcelableExtra<RestaurentHistoryResponse?>("data")
        address.text = data?.address
        name.text = data?.businessName
        Glide.with(this).load(data?.businessLogoPath)
            .into(imageView)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        // go to specials on sale
        btnSpecialsOnSale.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                startActivity(Intent(this@PartnerDetailsActicity, AllGamesActivity::class.java).putExtra("businessid",data?.businessId.toString()))
            }
        })
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        if (!data?.latitude!!.equals("") && !data?.longitude!!.equals("")) {
            val sydney = LatLng(data?.latitude!!.toDouble(), data?.longitude!!.toDouble())
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        }

    }
}