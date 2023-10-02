package com.example.youtube.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youtube.BuildConfig
import com.example.youtube.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BuildConfig.API_KEY
        BuildConfig.BASE_URL
    }
}