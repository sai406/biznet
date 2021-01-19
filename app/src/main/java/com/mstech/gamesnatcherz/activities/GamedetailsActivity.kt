package com.mstech.gamesnatcherz.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.mstech.gamesnatcherz.R
import kotlinx.android.synthetic.main.activity_gamedetails.*
import java.lang.Exception

class GamedetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gamedetails)
        try{
            gamedescription.text=intent.extras?.getString("gametext")
            gamename.text=intent.extras?.getString("gamename")
            Glide.with(this)  //2
                .load(intent.extras?.getString("gameimage")) //3
                .placeholder(R.drawable.loading) //5
                .error(R.drawable.error) //6
                .fallback(R.drawable.loading) //7
                .into(gameimage)
            Glide.with(this)  //2
                .load(intent.extras?.getString("businesslogo")) //3
                .placeholder(R.drawable.loading) //5
                .error(R.drawable.error) //6
                .fallback(R.drawable.loading) //7
                .into(businesslogo)
            businessname.text=intent.extras?.getString("businessname")
        }catch (e:Exception){
            e.printStackTrace()
        }
        play.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this,
                    SwipeGameActivity::class.java
                ).putExtra("gameid", intent.extras?.getString("gameid").toString())

            )
            finish()
        })
    }
}