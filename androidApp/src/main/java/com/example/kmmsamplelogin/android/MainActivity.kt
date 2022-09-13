package com.example.kmmsamplelogin.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kmmsamplelogin.Greeting
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint

fun greet(): String {
    return Greeting().greeting()
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}
