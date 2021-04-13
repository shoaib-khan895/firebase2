package com.example.firebase

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Device token is used to send test notifications
        //getFirebaseFCMDeviceToken()

        val imageURL = getCustomDataFromFCM()
        if (imageURL != null) {
            updateUI(imageURL)
        } else {
            Toast.makeText(this, "Make sure notification has correct image URL", Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * Update the UI/Update the Image as per received URL
     */
    private fun updateUI(imageURL: String) {
        Glide
            .with(this)
            .load(imageURL)
            .into(imageView)
    }

    private fun getCustomDataFromFCM(): String? {
        Log.i(TAG, "${ intent.extras?.getString(MyFirebaseMessagingService.IMAGE_URL_KEY) }")
        return intent.extras?.getString(MyFirebaseMessagingService.IMAGE_URL_KEY)
    }

}