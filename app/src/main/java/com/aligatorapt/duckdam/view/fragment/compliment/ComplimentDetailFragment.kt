package com.aligatorapt.duckdam.view.fragment.compliment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import android.text.method.ScrollingMovementMethod
import androidx.lifecycle.Observer
import com.aligatorapt.duckdam.databinding.FragmentComplimentDetailBinding
import com.aligatorapt.duckdam.view.fragment.home.ScrollVerticalFragment
import com.aligatorapt.duckdam.viewModel.ComplimentSingleton
import java.text.SimpleDateFormat

class ComplimentDetailFragment : Fragment() {
    private var _binding: FragmentComplimentDetailBinding? = null
    private val binding: FragmentComplimentDetailBinding get() = _binding!!

    private val model = ComplimentSingleton.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComplimentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        val mActivity = activity as NavigationActivity
        binding.apply {
            mActivity.showTabView(false)

            //데이터 설정
            model?.detailCompliment?.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    val dateFormat = SimpleDateFormat("yyyy.MM.dd")
                    sticker.setImageResource(
                        resources.obtainTypedArray(R.array.stickerImg)
                            .getResourceId(it.stickerNum, 0)
                    )
                    date.text = dateFormat.format(it.date)
                    content.text = it.message
                    from.text = "${it.fromName}가"
                    complimentBtn.text = "${it.fromName}에게 칭찬하러 가기"
                    addFriendBtn.text = "${it.fromName} 친구 추가하기"
                }
            })

            content.movementMethod = ScrollingMovementMethod()
            complimentDetailBackBtn.setOnClickListener {
                model?.isTodayToDetail?.observe(viewLifecycleOwner, Observer {
                    if(it == true){
                        mActivity.selectedBottomNavigationItem(R.id.tab_home)
                    }else{
                        mActivity.changeFragment(ScrollVerticalFragment())
                    }
                })
            }
            complimentBtn.setOnClickListener {
                mActivity.selectedBottomNavigationItem(R.id.tab_compliment)
            }

            addFriendBtn.setOnClickListener {
                mActivity.selectedBottomNavigationItem(R.id.tab_friend)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
