package com.example.levi9application

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.levi9application.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private var keepSplashOnScreen = true
    private val delay = 2000L
    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)

        binding = ActivityAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)

//        val button: Button = findViewById(R.id.buttonNavigate)
//        button.setOnClickListener {
//            // Create an Intent to start the SecondActivity
//            val intent = Intent(this, MainActivity::class.java)
//
//            // Start the SecondActivity
//            startActivity(intent)
//        }
    }


}