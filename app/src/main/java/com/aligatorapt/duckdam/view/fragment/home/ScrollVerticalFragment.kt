package com.aligatorapt.duckdam.view.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aligatorapt.duckdam.databinding.FragmentScrollVerticalBinding

class ScrollVerticalFragment : Fragment() {
    private var _binding: FragmentScrollVerticalBinding? = null
    private val binding: FragmentScrollVerticalBinding get() = _binding!!

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

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
