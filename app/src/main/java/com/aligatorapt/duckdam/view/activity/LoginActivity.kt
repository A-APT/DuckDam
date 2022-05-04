package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            loginSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
                startActivity(intent)
            }
        }
    }
}