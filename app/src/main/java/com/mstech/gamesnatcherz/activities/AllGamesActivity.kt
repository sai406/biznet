package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mstech.gamesnatcherz.R
import kotlinx.android.synthetic.main.activity_all_games.*

class AllGamesActivity : AppCompatActivity() {
    var businessid = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_games)
        supportActionBar?.title = "Games"
                try{
            businessid = intent?.extras!!.getString("businessid").toString()
                }catch ( e:Exception){
            e.printStackTrace()
                }
        survey.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, Survey::class.java).putExtra(
                "data",
                businessid.toString()))
        })
        textquiz.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, TextQuiz::class.java).putExtra(
                "data",
                businessid))
        })
        smartquiz.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, SmartQuiz::class.java).putExtra(
                "data",
            businessid))
        })
        swipe.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, GamesListActivity::class.java).putExtra(
                "data",
                businessid
            ))
        })
    }
}