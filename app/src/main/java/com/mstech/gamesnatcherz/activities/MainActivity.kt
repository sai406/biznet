package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.SPStaticUtils
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R

class MainActivity : AppIntro2() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        addSlide(
            AppIntroFragment.newInstance(
            "Welcome!",
            "Game Snatchertz",
                backgroundDrawable = R.drawable.slide1
        ))

        addSlide(AppIntroFragment.newInstance(
            "Welcome!",
            "Game Snatchertz",
            backgroundDrawable = R.drawable.slide2
        ))

        addSlide(AppIntroFragment.newInstance(
            "Welcome!",
            "Game Snatchertz",
            backgroundDrawable = R.drawable.slide3
        ))


        // Set intro custom background
        backgroundResource = R.drawable.slide1

        // Change the color of the dot indicator.
        setIndicatorColor(Color.RED, Color.BLACK)
    }

    public override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        if (SPStaticUtils.getString(SharedKey.ISLOGIN, "false").equals("true")) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    public override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        if (SPStaticUtils.getString(SharedKey.ISLOGIN, "false").equals("true")) {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    }
