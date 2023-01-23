package com.mstech.gamesnatcherz.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.SPStaticUtils
import com.blankj.utilcode.util.ToastUtils
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.mstech.gamesnatcherz.model.SharedKey
import com.mstech.gamesnatcherz.R
import com.mstech.gamesnatcherz.Utils.MyUtils.showProgress
import com.mstech.gamesnatcherz.utils.RetrofitApi
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_receipts.*
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.IOException

class ReceiptsActivity : AppCompatActivity() {
    private var mImageByte: ByteArray? = null
var businessid=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipts)
        businessid = intent?.getStringExtra("data").toString()
        Log.d("TAG", "onCreate: "+businessid)
        selectImage.setOnClickListener(View.OnClickListener {
            CropImage.activity()
                .start(this);
        })
        post.setOnClickListener(View.OnClickListener {
            lifecycleScope.launch {
                if (mImageByte!=null){
                    sendImage()
                }else{
                    ToastUtils.showShort("Select Image")
                }

            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("TAG", "onActivityResult: ")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result: CropImage.ActivityResult = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                if (result != null) {
                    val uri: Uri = result.getUri() //path of image in phone
                    image.setImageURI(uri) //set image in imageview

                    try {
                        val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                            ImageDecoder.decodeBitmap(
                                ImageDecoder.createSource(
                                    this.contentResolver,
                                    uri
                                )
                            )
                        } else {
                            MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                        }
                        if (bitmap != null) {
                            val outputStream = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
                            mImageByte = outputStream.toByteArray()
                        }
                        captured.setText("") //so that previous text don't get append with new one
                        extractTextFromImage(uri, bitmap) //method to extract text from image
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun extractTextFromImage(uri: Uri, bitmap: Bitmap) {
        //FirebaseVisionImage Object
        val image = FirebaseVisionImage.fromFilePath(this, uri)
        //FirebaseVisionTextRecognizer's object
        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer
        //Pass the image object to the detector processImage method
        detector.processImage(image).addOnSuccessListener { firebaseVisionText ->

captured.setText(firebaseVisionText.text)
                for ( block in firebaseVisionText.textBlocks) {
                    val blockText = block.text

                }





        }.addOnFailureListener { }
    }
    private suspend fun sendImage() {
    showProgress(this, true)
        var body = JSONObject()
        body.put("CustomerId", SPStaticUtils.getString(SharedKey.CUSTOMER_ID, "0"))
        body.put("BusinessId", "1")
        body.put("BillData", captured.text.toString())
        body.put("Imgfile", Base64.encodeToString(mImageByte, Base64.DEFAULT))
        body.put("extension", ".jpg")
        var finalbody = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            ((body)).toString()
        )

        val response = RetrofitApi().sendBill(finalbody)
        if (response.isSuccessful) {

            var res = response.body()!!
            Log.d("TAG", "sendImage: "+res.string())

        }else{
            ToastUtils.showShort(response.errorBody().toString())
        }
      showProgress(this, false)
    }
}