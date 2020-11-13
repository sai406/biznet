package com.mstech.gamesnatcherz.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ToastUtils
import com.mstech.gamesnatcherz.R
import kotlinx.android.synthetic.main.activity_qr_scanner.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class QrScannerActivity : AppCompatActivity() ,ZBarScannerView.ResultHandler {
    lateinit var scannerView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scanner)
        supportActionBar?.title=" Scan QR Code"
        initializeQRCamera()
        onClicks()
    }
    private fun initializeQRCamera() {
        scannerView = ZBarScannerView(this)
        scannerView.setResultHandler(this)
        scannerView.setBackgroundColor(
            ContextCompat.getColor(
                this,
                R.color.colorTranslucent
            )
        )
        scannerView.setBorderColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimaryDark
            )
        )
        scannerView.setLaserColor(
            ContextCompat.getColor(
                this,
                R.color.colorPrimaryDark
            )
        )
        scannerView.setBorderStrokeWidth(10)
        scannerView.setSquareViewFinder(true)
        scannerView.setupScanner()
        scannerView.setAutoFocus(true)
        startQRCamera()
        containerScanner.addView(scannerView)
    }


    private fun startQRCamera() {
        scannerView.startCamera()
    }


    private fun onClicks() {
        flashToggle.setOnClickListener {
            if (flashToggle.isSelected) {
                offFlashLight()
            } else {
                onFlashLight()
            }
        }
    }

    private fun onFlashLight() {
        flashToggle.isSelected = true
        scannerView.flash = true
    }

    private fun offFlashLight() {
        flashToggle.isSelected = false
        scannerView.flash = false
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
//        Toast.makeText(this, rawResult?.contents, Toast.LENGTH_LONG).show()
        scannerView.resumeCameraPreview(this)
        if (rawResult?.contents!!.contains("gamesnatcherz")) {

            Log.d("TAG", "handleResult: "+ rawResult.contents.toString())
            startActivity(
                Intent(this, GamesListActivity::class.java).putExtra(
                    "data",
                    rawResult.contents.toString().split("id=")[1]
                )
            )
        } else {
            ToastUtils.showShort("Please Scan GameSnatcherz QR Code")
        }


    }

}