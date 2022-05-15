package com.aligatorapt.duckdam.view.fragment.home

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentScrollHorizontalBinding
import com.aligatorapt.duckdam.view.activity.DuckdamDetailActivity
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.activity.VendingActivity
import com.aligatorapt.duckdam.view.adapter.HomeStickerAdapter
import com.aligatorapt.duckdam.view.data.AllComplimentChild

class ScrollHorizontalFragment : Fragment() {
    private var _binding: FragmentScrollHorizontalBinding? = null
    private val binding: FragmentScrollHorizontalBinding get() = _binding!!

    private lateinit var complimentAdapter: HomeStickerAdapter

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
            //배너
            complimentAdapter = HomeStickerAdapter(setList())
            complimentAdapter.itemClickListener = object : HomeStickerAdapter.OnItemClickListener {
                override fun OnItemClick(
                    holder: HomeStickerAdapter.MyViewHolder,
                    view: View,
                    data: AllComplimentChild,
                    position: Int
                ) {
                    val intent = Intent(mActivity, DuckdamDetailActivity::class.java)
                    intent.putExtra("item", data)
                    startActivity(intent)
                }
            }
            viewpager.adapter = complimentAdapter
            viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

            //칭찬 개수 설정 및 데이터 설정
            todayCount.text = complimentAdapter.itemCount.toString()
            setContent(complimentAdapter.getItem(0))
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

    private fun setContent(item: AllComplimentChild){
        binding.apply {
            content.text = item.content
            nickname.text = item.from
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setList(): ArrayList<AllComplimentChild> {
        return arrayListOf(
            AllComplimentChild(
                sticker = R.drawable.sticker06,
                date = "2022.05.11",
                content = "첫번째 내용이야 오늘 하루 도움을 줘서 너무 고마워! 네가 없었으면 우리 팀은 이기지 못했을 거야 :)오늘 하루 도움을 줘서 너무 고마워! 진짜 너무 고마워서 그런데 나랑 같이 밥먹으러 가자 내가 밥사줄게, 학교 근처에 미분당 알아? 나 거기 못간지 3년째야,, 진짜 너무 먹고싶었는데 갈때 마다 브레이크타임이더라고,, 그러니까 꼭 3~5시 제외하고 약속 잡다!! 아 생각만 해도 맛있겠다",
                from = "악어"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker07,
                date = "2022.05.11",
                content = "두번째 내용이야 너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "오리"
            ),
            AllComplimentChild(
                sticker = R.drawable.sticker08,
                date = "2022.05.11",
                content = "세번째 내용이야 너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                from = "악어"
            ),
        )
    }
}
