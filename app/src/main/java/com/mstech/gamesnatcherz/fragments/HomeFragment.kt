package com.mstech.gamesnatcherz.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.Model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.activities.GsPartnersActivity
import com.mstech.gamesnatcherz.activities.NotificationsActivity
import com.mstech.gamesnatcherz.activities.ProfileActivity
import com.mstech.gamesnatcherz.activities.QrScannerActivity
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        root.promo.setOnClickListener(View.OnClickListener {
           requireActivity().startActivity(Intent(activity, QrScannerActivity::class.java))
        })
        root.account.setOnClickListener(View.OnClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                requireActivity().startActivity(Intent(activity, ProfileActivity::class.java))
            }else{
                ToastUtils.showShort("please login to play the game")
            }

        })
        root.notification.setOnClickListener(View.OnClickListener {
            if (!SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0").equals("0")) {
                requireActivity().startActivity(Intent(activity, NotificationsActivity::class.java))
            }else{
                ToastUtils.showShort("please login to play the game")
            }

        })
        root.gspartner.setOnClickListener(View.OnClickListener {
           requireActivity().startActivity(Intent(activity, GsPartnersActivity::class.java))
        })

        return root
    }
}