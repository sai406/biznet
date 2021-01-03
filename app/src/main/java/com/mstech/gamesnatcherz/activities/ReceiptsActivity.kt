package com.mstech.gamesnatcherz.activities

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.mstech.gamesnatcherz.R
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_receipts.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class ReceiptsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipts)
        selectImage.setOnClickListener(View.OnClickListener {
            CropImage.activity()
                .start(this);
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
                        captured.setText("") //so that previous text don't get append with new one
                        extractTextFromImage(uri) //method to extract text from image
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    @Throws(IOException::class)
    private fun extractTextFromImage(uri: Uri) {
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
}