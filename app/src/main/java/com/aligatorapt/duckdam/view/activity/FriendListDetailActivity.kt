package com.aligatorapt.duckdam.view.activity

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityFriendlistDetailBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.retrofit.callback.ComplimentsCallback
import com.aligatorapt.duckdam.view.adapter.FriendListDetailAdapter
import com.aligatorapt.duckdam.viewModel.ComplimentSingleton

class FriendListDetailActivity: AppCompatActivity() {
    lateinit var binding: ActivityFriendlistDetailBinding
    private val model = ComplimentSingleton.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendlistDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init(){
        //친구 이름으로 칭찬내역 찾기
        val friend = intent.getSerializableExtra("data") as UserResponseDto

        binding.apply {
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            fldetailRv.layoutManager = linearLayoutManager
            val friendListDetailAdapter = FriendListDetailAdapter(applicationContext, arrayListOf())
            fldetailRv.adapter = friendListDetailAdapter

            fldetailName.text = friend.name
            if(friend.profile != null){
                val decodedImageBytes: ByteArray = Base64.decode(friend.profile, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.size)
                fldetailSticker.setImageBitmap(bitmap)
            }else {
                fldetailSticker.setImageResource(R.drawable.small_sample)
            }

            model?.findComplimentsByFromAndTo(
                _toId = friend.uid,
                object: ComplimentsCallback{
                    override fun complimentsCallback(
                        flag: Boolean,
                        data: ArrayList<ComplimentResponseDto>?
                    ) {
                        if(flag){
                            if(data!!.isNotEmpty()){
                                emptyResult.visibility = View.GONE
                                fldetailRv.visibility = View.VISIBLE
                                friendListDetailAdapter.setData(data)
                            }else{
                                emptyResult.visibility = View.VISIBLE
                                fldetailRv.visibility = View.GONE

                            }
                        }
                    }
            })

            fldetailBackTv.setOnClickListener {
                finish()
            }
            fldetailCompliment.setOnClickListener {
                setResult(RESULT_OK,intent)
                finish()
            }
        }

    }
}