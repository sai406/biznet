package com.mstech.gamesnatcherz.activities

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.adapter.RestaurentHistoryAdapter
import com.mstech.gamesnatcherz.utils.RetrofitApi
import kotlinx.android.synthetic.main.activity_gs_partners.*
import kotlinx.coroutines.launch
import java.util.*

class GsPartnersActivity : AppCompatActivity() {
    var adapter: RestaurentHistoryAdapter? = null
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    var latitude = 0.0
    var longitude = 0.0

    //the permission id is just an int that must be unique so you can use any number you want
    private var PERMISSION_ID = 36

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gs_partners)
        supportActionBar?.hide()
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
//        getLastLocation()
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = linearLayoutManager


        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter?.filter(newText)
                return false
            }

        })
        val searchIcon = search.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.WHITE)
        val cancelIcon = search.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.WHITE)
        val textView = search.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.WHITE)
        lifecycleScope.launch {
            getRestaurentHistory(latitude, longitude)
        }
    }

    private suspend fun getRestaurentHistory(lat: Double, lan: Double) {
        try {
            MyUtils.showProgress(this, true)
//            var obj = JSONObject()
//            obj.put("AdminId", 1)
//            obj.put("BusinessId",0)
//            obj.put("FromDate", "")
//            obj.put("ToDate", "")
//            obj.put("Pi", 0)
//            obj.put("Ps", -1)
//            var body = RequestBody.create(
//                okhttp3.MediaType.parse("application/json; charset=utf-8"),
//                ((obj)).toString()
//            )
            val response = RetrofitApi().getPartners(
                "1",
                lat,
                lan,
                "0",
                SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0")
            )
            Log.d("TAG", "getRestaurentHistory: " + lat + lan)
            try {
                if (response.isSuccessful) {
                    var devicelist = response.body()
                    adapter =
                        devicelist.let {
                            it?.let { it1 ->
                                RestaurentHistoryAdapter(
                                    this,
                                    it1,
                                    "gs"
                                )
                            }
                        }
                    recyclerView?.adapter = adapter
                    adapter?.notifyDataSetChanged()
                    Log.d("TAG", "getRestaurentHistory: " + response.body().toString())
                } else {
                    LogUtils.e(response.errorBody()?.string())
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
            MyUtils.showProgress(this, false)
        } catch (e: Exception) {
            e.printStackTrace()
            MyUtils.showProgress(this, false)
        }

    }

    //function to get the city name
//    private fun getCityName(lat:Double,long:Double) : String{
//
//        var geoCoder = Geocoder(this, Locale.getDefault())
//        var adress : MutableList<Address> = geoCoder.getFromLocation(lat,long,1)
//
//        val cityName = adress.get(0).adminArea
//        return cityName
//    }
    //function to get the countryname
    private fun getCountryName(lat: Double, long: Double): String {

        var geoCoder = Geocoder(this, Locale.getDefault())
        var adress = geoCoder.getFromLocation(lat, long, 1)

        val countryName = adress?.get(0)?.countryName
        return countryName ?: ""
    }
    //Now we will create a function that will allow us to get the last location

    //    private fun getLastLocation(){
//        //first we check permission
//        if (checkPermission()){
//            // Now we check the location service is enabled
//            if (isLocationEnabled()){
//                //Now let's get the location
//                fusedLocationProviderClient.lastLocation.addOnCompleteListener{task ->
//                    var location : Location? = task.result
//                    if(location == null){
//                        //If the location is null we will get the new user location
//                        //so we need to create a new function
//                        getNewLocation()
//                    }else{
//                        latitude= location.latitude
//                        longitude = location.longitude
//                        if (!NetworkUtils.isConnected()) {
//                            ToastUtils.showShort("No Internet Connection")
//                        } else {
//                            lifecycleScope.launch {
//                                getRestaurentHistory(latitude,longitude)
//                            }
//                        }
//                    }
//                }
//            }else{
//                Toast.makeText(this, "Please enable your location service to see nearby", Toast.LENGTH_SHORT).show()
//                if (!NetworkUtils.isConnected()) {
//                    ToastUtils.showShort("No Internet Connection")
//                } else {
//                    lifecycleScope.launch {
//                        getRestaurentHistory(latitude,longitude)
//                    }
//                }
//            }
//        }else{
//            requestPermission()
//        }
//    }
    //First we need to create a function that will check the uses permission
    private fun checkPermission(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager
                .PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager
                .PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    //Now we need to create a function that will allow us to get user's permission
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ),
            PERMISSION_ID
        )
    }

    // Now we need a function that check if the location service of the device is enabled

    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //this is a built in function that check the permission result
        //we will use it just for debugging our code
        if (requestCode == PERMISSION_ID) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("Debug", "You have the permission")
            } else {
                Toast.makeText(
                    this,
                    "Please give Permission to see nearby business",
                    Toast.LENGTH_SHORT
                ).show()
//                if (!NetworkUtils.isConnected()) {
//                    ToastUtils.showShort("No Internet Connection")
//                } else {
//                    lifecycleScope.launch {
//                        getRestaurentHistory(latitude,longitude)
//                    }
//                }
            }
        }
    }

    private fun getNewLocation() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 2
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
//        fusedLocationProviderClient.requestLocationUpdates(
//            locationRequest, locationCallBack, Looper.myLooper()
//        )
    }


//    private var locationCallBack = object : LocationCallback(){
//        override fun onLocationResult(p0: LocationResult) {
//            var lastLocation = p0.lastLocation
////            Locationtxt.text = "Your current coordinates are :\nLat:"+lastLocation.latitude+"; Long:"+lastLocation.longitude+
////                    "\n Your City: "+getCityName(lastLocation.latitude,lastLocation.longitude)+", your country: "+getCountryName(lastLocation.latitude,lastLocation.longitude)
//               ToastUtils.showShort(latitude.toString())
//                latitude = lastLocation.latitude
//            longitude = lastLocation.longitude
//            lifecycleScope.launch {
//                getRestaurentHistory(latitude,longitude)
//            }
//        }
//    }
}
