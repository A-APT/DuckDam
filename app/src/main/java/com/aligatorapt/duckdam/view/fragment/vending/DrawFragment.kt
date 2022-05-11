package com.aligatorapt.duckdam.view.fragment.vending

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aligatorapt.duckdam.databinding.FragmentDrawBinding
import com.aligatorapt.duckdam.view.activity.VendingActivity


class DrawFragment : Fragment() {
    private var _binding: FragmentDrawBinding? = null
    private val binding: FragmentDrawBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrawBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val mActivity = activity as VendingActivity
        binding.apply {
            drawBtn.setOnClickListener {
                mActivity.changeFragment(DrawResultFragment())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
