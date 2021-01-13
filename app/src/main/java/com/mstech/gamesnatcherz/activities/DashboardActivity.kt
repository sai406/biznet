package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.navigation.NavigationView
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R


class DashboardActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboar)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        val logoutItem = navView.menu.findItem(R.id.logout)
        val game = navView.menu.findItem(R.id.smartquiz)
        val swipe = navView.menu.findItem(R.id.swipe)
        val textquiz = navView.menu.findItem(R.id.textquiz)
        val survey = navView.menu.findItem(R.id.survey)
        val favorite = navView.menu.findItem(R.id.favorites)
        val receipt = navView.menu.findItem(R.id.receipts)
        logoutItem.setOnMenuItemClickListener {
            SPStaticUtils.clear()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
            true
        }
        game.setOnMenuItemClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                startActivity(Intent(this, SmartQuiz::class.java))
            }else{
                ToastUtils.showShort("please login to play the game")
            }
            true
        }

            swipe.setOnMenuItemClickListener {
                if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                    startActivity(Intent(this, GamesListActivity::class.java))
                }else{
                    ToastUtils.showShort("please login to play the game")
                }
            true
        }
        textquiz.setOnMenuItemClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                startActivity(Intent(this, TextQuiz::class.java))
            }else{
                ToastUtils.showShort("please login to play the game")
            }
            true
        }
        survey.setOnMenuItemClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                startActivity(Intent(this, Survey::class.java))
            }else{
                ToastUtils.showShort("please login to play the game")
            }
            true
        }
        favorite.setOnMenuItemClickListener {
                startActivity(Intent(this, FavoritesActivity::class.java))

            true
        }
        receipt.setOnMenuItemClickListener {
                startActivity(Intent(this, ReceiptsRestaurentActivity::class.java))

            true
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}