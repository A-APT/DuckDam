package com.aligatorapt.duckdam.view.fragment.vending

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aligatorapt.duckdam.databinding.FragmentDrawResultBinding

class DrawResultFragment : Fragment() {
    private var _binding: FragmentDrawResultBinding? = null
    private val binding: FragmentDrawResultBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrawResultBinding.inflate(inflater, container, false)
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
