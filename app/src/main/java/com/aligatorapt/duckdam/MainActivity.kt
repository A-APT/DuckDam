package com.aligatorapt.duckdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aligatorapt.duckdam.databinding.ActivityMainBinding
import com.aligatorapt.duckdam.view.activity.SplashActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            splash.setOnClickListener {
                val intent = Intent(this@MainActivity,SplashActivity::class.java)
                startActivity(intent)
            }
        }

    }
}