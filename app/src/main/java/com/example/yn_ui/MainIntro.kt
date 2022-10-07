package com.example.yn_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock

class MainIntro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_intro)

        var handler = Handler()
        handler.postDelayed( {
            var intent = Intent( this, MainActivity::class.java)
            startActivity(intent)
        }, 1000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

}