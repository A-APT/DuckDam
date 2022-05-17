package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityFriendlistDetailBinding
import com.aligatorapt.duckdam.view.adapter.FriendListDetailAdapter
import com.aligatorapt.duckdam.view.data.AllComplimentChild
import com.aligatorapt.duckdam.view.data.SelectList
import com.aligatorapt.duckdam.view.fragment.compliment.ComplimentFragment

class FriendListDetailActivity: AppCompatActivity() {
    lateinit var binding: ActivityFriendlistDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendlistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        //친구 이름으로 칭찬내역 찾기
        val selectList = intent.getSerializableExtra("data") as SelectList

        binding.apply {
            fldetailName.text = selectList.name
            fldetailSticker.setImageResource(selectList.sticker)
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            fldetailRv.layoutManager = linearLayoutManager
            val friendListDetailAdapter = FriendListDetailAdapter(applicationContext, setList())
            fldetailRv.adapter = friendListDetailAdapter

            fldetailBackTv.setOnClickListener {
                finish()
            }
            fldetailCompliment.setOnClickListener {
                intent.putExtra("selectList",selectList)
                setResult(RESULT_OK,intent)
                finish()
            }
        }

    }

    private fun setList(): ArrayList<AllComplimentChild> {
        return arrayListOf(
            AllComplimentChild(
                sticker = R.drawable.sticker06,
                date = "2022.01.02",
                content = "오늘 하루 도움을 줘서 너무 고마워! 네가 없었으면 우리 팀은 이기지 못했을 거야 :)",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker07,
                date = "2022.01.02",
                content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker08,
                date = "2022.01.20",
                content = "세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker06,
                date = "2022.01.02",
                content = "오늘 하루 도움을 줘서 너무 고마워! 네가 없었으면 우리 팀은 이기지 못했을 거야 :)",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker07,
                date = "2022.01.02",
                content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker08,
                date = "2022.01.20",
                content = "세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker06,
                date = "2022.01.02",
                content = "오늘 하루 도움을 줘서 너무 고마워! 네가 없었으면 우리 팀은 이기지 못했을 거야 :)",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker07,
                date = "2022.01.02",
                content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "어깨피자"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker08,
                date = "2022.01.20",
                content = "세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "어깨피자"
            ),
        )
    }
}