package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.R
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
        binding.apply {
            content.movementMethod = ScrollingMovementMethod()
            complimentDetailBackBtn.setOnClickListener {
                onBackPressed()
            }
            complimentBtn.setOnClickListener {
                val intent = Intent(this@DuckdamDetailActivity, NavigationActivity::class.java)
                intent.putExtra("position", R.id.tab_compliment)
                startActivity(intent)
            }

            addFriendBtn.setOnClickListener {
                val intent = Intent(this@DuckdamDetailActivity, NavigationActivity::class.java)
                intent.putExtra("position", R.id.tab_friend)
                startActivity(intent)
            }
        }
    }
}
