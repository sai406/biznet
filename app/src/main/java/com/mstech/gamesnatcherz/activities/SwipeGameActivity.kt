package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils
import com.mstech.gamesnatcherz.utils.RetrofitApi
import com.mstech.gamesnatcherz.utils.ScratchImageView
import kotlinx.android.synthetic.main.activity_swipe_game.*
import kotlinx.coroutines.launch

class SwipeGameActivity : AppCompatActivity() {
    var prizeid ="0"
    var gameid = "0"
    var promocheck="1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_game)
        supportActionBar?.title = "Swipe and Win"
        gameid = intent.extras?.getString("gameid","0").toString()
        lifecycleScope.launch {
            if (NetworkUtils.isConnected()){
                getPrize(gameid,SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"))
            }
        }

        scratchimage.setRevealListener(object : ScratchImageView.IRevealListener {
            override fun onRevealed(tv: ScratchImageView?) {
                // on reveal
            }
            override fun onRevealPercentChangedListener(
                siv: ScratchImageView?,
                percent: Float
            ) {
                // on image percent reveal
                if (percent > 0.5) {
                    scratchimage.reveal()
                    prizemsg.visibility = View.VISIBLE
                    claim.visibility = View.VISIBLE
                }
            }
        })
        promo.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton, b ->
            if (b==true){
                promocheck="1"
            }else{
                promocheck="0"
            }
        })
        claim.setOnClickListener(View.OnClickListener {
            if (SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0").equals("0")){
                SharedKey.ISPRIZE= true
                ToastUtils.showShort("Please Login to redeem the Prize..")
                startActivity(Intent(this,LoginActivity::class.java))
            }else{
//                startActivity(Intent(this,Prizes_Claim_Activity::class.java).putExtra("prizeid",prizeid).putExtra("gameid",gameid))
//                finish()
                lifecycleScope.launch {
                    if (NetworkUtils.isConnected()){
                        redeemPrize(SPStaticUtils.getString(SharedKey.CUSTOMER_ID,"0"),SPStaticUtils.getString(SharedKey.GAMEID,"0"),SPStaticUtils.getString(SharedKey.PRIZEID,"0"))
                    }

                }
            }

        })
    }
    private suspend fun getPrize(id:String,cid:String) {
        MyUtils.showProgress(this, true)
        val response = RetrofitApi().getPrizebyCid(id,cid)
        if (response.isSuccessful){
            if (response.body()?.status!!) {
                if (response.body()?.success!!) {
                    var prizedata = response.body()?.data
                    gamename.text = prizedata?.gameDetails?.title
                    details.text = prizedata?.gameDetails?.description
                    prizemsg.text = prizedata?.prizeDetails?.prizeMessage.toString()
                    prizeid = prizedata?.prizeDetails?.prizeNumber.toString()
                    gameid = prizedata?.gameDetails?.gameId.toString()
                    SPStaticUtils.put(SharedKey.PRIZEID,prizeid)
                    SPStaticUtils.put(SharedKey.GAMEID,gameid)
                    Glide.with(this)
                        .applyDefaultRequestOptions(
                            RequestOptions()
                                .placeholder(R.drawable.loading)
                                .error(R.drawable.ic_launcher_background)
                        )
                        .load(prizedata?.prizeDetails?.prizePath)
                        .into(scratchimage)
                } else {
                    ToastUtils.showShort("Please try again")
                }
            }else{
                ToastUtils.showShort("Server Error Please try again")
            }
        }else{
            ToastUtils.showShort(response.errorBody().toString())
        }
        MyUtils.showProgress(this, false)
    }
 private suspend fun redeemPrize(cid:String,id:String,prizenum:String) {
        MyUtils.showProgress(this, true)
        val response = RetrofitApi().redeemPrize(cid,id,prizenum,promocheck)
     LogUtils.e(cid+id+prizenum+"")
        if (response.isSuccessful){
            if (response.body()?.status!!) {
                if (response.body()?.success!!) {
                    var prizedata = response.body()?.data
                    ToastUtils.showShort(response.body()?.data!!.message)
                    finish()

                } else {
                    ToastUtils.showShort("Please try again")
                }
            }else{
                ToastUtils.showShort("Server Error Please try again")
            }
        }else{
            ToastUtils.showShort(response.errorBody().toString())
        }
        MyUtils.showProgress(this, false)
    }

}