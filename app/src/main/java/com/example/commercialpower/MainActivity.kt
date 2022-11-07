package com.example.commercialpower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val windowManager = window
        windowManager.statusBarColor = ContextCompat.getColor(this, com.example.onboarding.R.color.white)

    }
}