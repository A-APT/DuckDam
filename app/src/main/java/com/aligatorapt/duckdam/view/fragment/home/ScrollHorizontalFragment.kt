package com.aligatorapt.duckdam.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aligatorapt.duckdam.databinding.FragmentScrollHorizontalBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.retrofit.callback.ComplimentsCallback
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.activity.VendingActivity
import com.aligatorapt.duckdam.view.adapter.HomeStickerAdapter
import com.aligatorapt.duckdam.view.fragment.compliment.ComplimentDetailFragment
import com.aligatorapt.duckdam.viewModel.ComplimentSingleton
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ScrollHorizontalFragment : Fragment() {
    private var _binding: FragmentScrollHorizontalBinding? = null
    private val binding: FragmentScrollHorizontalBinding get() = _binding!!

    private lateinit var complimentAdapter: HomeStickerAdapter

    private val model = ComplimentSingleton.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollHorizontalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val mActivity = activity as NavigationActivity
        binding.apply {
            //뷰페이저 어뎁터
            complimentAdapter = HomeStickerAdapter(arrayListOf(), requireActivity())
            complimentAdapter.itemClickListener = object : HomeStickerAdapter.OnItemClickListener {
                override fun OnItemClick(
                    holder: HomeStickerAdapter.MyViewHolder,
                    view: View,
                    data: ComplimentResponseDto,
                    position: Int
                ) {
                    model?.setDetailCompliment(data, true)
                    mActivity.changeFragment(ComplimentDetailFragment())
                }
            }
            viewpager.adapter = complimentAdapter
            viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewpager.isUserInputEnabled = false

            //데이터 가져오기
            model?.findCompliments(object: ComplimentsCallback {
                override fun complimentsCallback(
                    flag: Boolean,
                    data: ArrayList<ComplimentResponseDto>?
                ) {
                    if(flag){
                        if (data != null) {
                            Log.e("DATA::", data.toString())

                            if (data.isNotEmpty()) {
                                model.setCompliments(data)

                                val todayCompliment = ArrayList<ComplimentResponseDto>()

                                val dateFormat = SimpleDateFormat("yyyy-MM-dd")
                                val timeNow = dateFormat.format(Date(System.currentTimeMillis()))

                                for (compliment in data) {
                                    if (timeNow == dateFormat.format(compliment.date))
                                        todayCompliment.add(compliment)
                                }

                                model.setTodayCompliments(todayCompliment)
                                complimentAdapter.setData(todayCompliment)
                                setContent(complimentAdapter.getItem(0))
                                todayCount.text = complimentAdapter.itemCount.toString()

                                showCompliment.visibility = View.VISIBLE
                                emptyResult.visibility = View.GONE
                            } else {
                                showCompliment.visibility = View.GONE
                                emptyResult.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            })

            //스크롤 설정
            content.movementMethod = ScrollingMovementMethod()

            //배너 indicator
            leftBtn.setOnClickListener {
                val current = viewpager.currentItem
                if (current == 0){
                    viewpager.setCurrentItem(complimentAdapter.itemCount-1, false)
                    complimentAdapter.getItem(current)
                    setContent(complimentAdapter.getItem(complimentAdapter.itemCount-1))
                }
                else{
                    viewpager.setCurrentItem(current-1, false)
                    setContent(complimentAdapter.getItem(current-1))
                }
            }

            rightBtn.setOnClickListener {
                val current = viewpager.currentItem
                if (current == complimentAdapter.itemCount-1){
                    viewpager.setCurrentItem(0, false)
                    setContent(complimentAdapter.getItem(0))
                }
                else{
                    viewpager.setCurrentItem(current+1, false)
                    setContent(complimentAdapter.getItem(current+1))
                }
            }

            randomCompliment.setOnClickListener {
                val intent = Intent(mActivity, VendingActivity::class.java)
                startActivity(intent)
            }
            allCompliment.setOnClickListener {
                mActivity.changeFragment(ScrollVerticalFragment())
            }
        }
    }

    private fun setContent(item: ComplimentResponseDto){
        binding.apply {
            content.text = item.message
            nickname.text = item.fromName
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
