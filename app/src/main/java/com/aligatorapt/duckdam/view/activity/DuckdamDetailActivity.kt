package com.aligatorapt.duckdam.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.databinding.ActivityDuckdamDetailBinding

class DuckdamDetailActivity : AppCompatActivity()  {
    lateinit var binding: ActivityDuckdamDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDuckdamDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {

    }
}
