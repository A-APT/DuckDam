package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityDuckdamDetailBinding
import com.aligatorapt.duckdam.view.data.AllComplimentChild

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
            //데이터 설정
            val item = intent.getSerializableExtra("item") as AllComplimentChild
            sticker.setImageResource(item.sticker)
            date.text = item.date
            content.text = item.content
            from.text = "${item.from}가"
            complimentBtn.text = "${item.from}에게 칭찬하러 가기"
            addFriendBtn.text = "${item.from} 친구 추가하기"

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
