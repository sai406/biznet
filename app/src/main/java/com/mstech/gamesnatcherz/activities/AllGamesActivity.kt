package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.model.SharedKey
import kotlinx.android.synthetic.main.activity_games.*
import kotlinx.android.synthetic.main.layout_header.*

class AllGamesActivity : AppCompatActivity() {
    var businessid = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        supportActionBar?.hide()
        ivBack.setOnClickListener {
            onBackPressed()
        }
        tvHeader.text = "Games"
        try {
            businessid = intent?.extras!!.getString("businessid").toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }
//        survey.setOnClickListener(View.OnClickListener {
//            startActivity(
//                Intent(this, Survey::class.java).putExtra(
//                    "data",
//                    businessid.toString()
//                )
//            )
//        })
        textquiz.setOnClickListener(View.OnClickListener {
//            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
//                startActivity(
//                    Intent(this, TextQuiz::class.java).putExtra(
//                        "data", businessid
//                    )
//                )
//            } else {
//                ToastUtils.showShort("please login to play the games")
//            }
            startActivity(
                Intent(this, Survey::class.java).putExtra(
                    "data",
                    businessid.toString()
                )
            )

        })
//        smartquiz.setOnClickListener(View.OnClickListener {
//            startActivity(
//                Intent(this, SmartQuiz::class.java).putExtra(
//                    "data",
//                    businessid
//                )
//            )
//        })
        swipe.setOnClickListener(View.OnClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                startActivity(
                    Intent(this, GamesListActivity::class.java).putExtra(
                        "data", businessid
                    )
                )
            } else {
                ToastUtils.showShort("please login to see the profile")
            }
        })
    }
}