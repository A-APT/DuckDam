package com.aligatorapt.duckdam.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityDataBinding
import com.aligatorapt.duckdam.databinding.ActivityFriendlistDetailBinding

class DataActivity : AppCompatActivity() {
    lateinit var binding: ActivityDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        binding.apply {
            backBtn.setOnClickListener {
                onBackPressed()
            }
        }
    }
}