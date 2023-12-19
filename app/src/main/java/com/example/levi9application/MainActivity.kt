package com.example.levi9application

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.levi9application.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var keepSplashOnScreen = true
    private val delay = 2000L
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.fragment)
        bottomNavigationView.setupWithNavController(navController)

        setSupportActionBar(binding.toolbar.root)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.profile, R.id.cocktails, R.id.favorites)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }


}