package com.aligatorapt.duckdam.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentScrollVerticalBinding
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.adapter.AllComplimentParentAdapter
import com.aligatorapt.duckdam.view.data.AllComplimentChild
import com.aligatorapt.duckdam.view.data.AllComplimentParent

class ScrollVerticalFragment : Fragment() {
    private var _binding: FragmentScrollVerticalBinding? = null
    private val binding: FragmentScrollVerticalBinding get() = _binding!!

    private lateinit var parentAdapter: AllComplimentParentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScrollVerticalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val mActivity = activity as NavigationActivity
        binding.apply {
            //group list 매니저 등록
            parentAdapter = AllComplimentParentAdapter(mActivity, setList())
            recyclerAllCompliment.adapter = parentAdapter

            allComplimentBackBtn.setOnClickListener {
                mActivity.selectedBottomNavigationItem(R.id.tab_home)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setList():ArrayList<AllComplimentParent>{
        return arrayListOf(
            AllComplimentParent(
                "2022.05.11",
                arrayListOf(
                    AllComplimentChild(
                        sticker = R.drawable.sticker06,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker07,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker08,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker09,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker10,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker05,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker01,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                )
            ),
            AllComplimentParent(
                "2022.05.10",
                arrayListOf(
                    AllComplimentChild(
                        sticker = R.drawable.sticker06,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker07,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                )
            ),
            AllComplimentParent(
                "2022.05.19",
                arrayListOf(
                    AllComplimentChild(
                        sticker = R.drawable.sticker06,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                    AllComplimentChild(
                        sticker = R.drawable.sticker07,
                        date = "2022.05.11",
                        content = "너무 고마운데 이걸 말로 어떻게 길게 표현하지 세상에서 제일 긴 말로 너를 칭찬하고 싶은데 이정도면 될까?",
                        from = "악어"
                    ),
                )
            ),
        )
    }
}
