package com.example.auth.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.auth.R
import com.example.home.views.HomeActivity

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val windowManager = window
        windowManager.statusBarColor = ContextCompat.getColor(this, R.color.white)

        val handle = Handler()


        handle.postDelayed(Runnable {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}