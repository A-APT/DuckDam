package com.aligatorapt.duckdam.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivitySignupBinding

class SignUpActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            signupBackIv.setOnClickListener {
                finish()
            }
        }
    }
}