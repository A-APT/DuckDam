package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityDuckdamDetailBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import java.text.SimpleDateFormat

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
            val item = intent.getSerializableExtra("item") as ComplimentResponseDto

            val dateFormat = SimpleDateFormat("yyyy.MM.dd")
            sticker.setImageResource(resources.obtainTypedArray(R.array.stickerImg).getResourceId(item.stickerNum, 0))
            date.text = dateFormat.format(item.date)
            content.text = item.message
            from.text = "${item.fromId}가" //Todo change nickname
            complimentBtn.text = "${item.fromId}에게 칭찬하러 가기"
            addFriendBtn.text = "${item.fromId} 친구 추가하기"

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
