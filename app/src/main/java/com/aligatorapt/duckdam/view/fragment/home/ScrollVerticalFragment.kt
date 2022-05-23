package com.aligatorapt.duckdam.view.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentScrollVerticalBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.adapter.AllComplimentParentAdapter
import com.aligatorapt.duckdam.view.data.AllComplimentParent
import com.aligatorapt.duckdam.viewModel.ComplimentSingleton
import java.text.SimpleDateFormat

class ScrollVerticalFragment : Fragment() {
    private var _binding: FragmentScrollVerticalBinding? = null
    private val binding: FragmentScrollVerticalBinding get() = _binding!!

    private lateinit var parentAdapter: AllComplimentParentAdapter

    private val model = ComplimentSingleton.getInstance()

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

    private fun init() {
        val mActivity = activity as NavigationActivity
        binding.apply {
            //group list 매니저 등록
            parentAdapter = AllComplimentParentAdapter(arrayListOf(), mActivity, model!!)
            recyclerAllCompliment.adapter = parentAdapter

            //데이터 가져오기
            model!!.compliments.observe(viewLifecycleOwner, Observer { list ->
                Log.e("ALL::", list.toString())
                val sortMap = mutableMapOf<String, ArrayList<ComplimentResponseDto>>()
                var allComplimentParent = ArrayList<AllComplimentParent>()

                val dateFormat = SimpleDateFormat("yyyy.MM.dd")

                if (list != null) {
                    list.sortedBy { it.date }
                    Log.e("ALL::", list.toString())

                    for (compliment in list) {
                        val date = dateFormat.format(compliment.date)
                        if (sortMap.containsKey(date)) {
                            sortMap[date]?.add(compliment)
                        } else {
                            sortMap[date] = arrayListOf(compliment)
                        }
                    }

                    for (map in sortMap) {
                        allComplimentParent.add(AllComplimentParent(map.key, map.value))
                    }
                    parentAdapter.setData(allComplimentParent)
                }
            })

            allComplimentBackBtn.setOnClickListener {
                mActivity.selectedBottomNavigationItem(R.id.tab_home)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
