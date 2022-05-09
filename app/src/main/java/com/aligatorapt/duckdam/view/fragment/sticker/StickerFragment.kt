package com.aligatorapt.duckdam.view.fragment.sticker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aligatorapt.duckdam.databinding.FragmentStickerBinding

class StickerFragment : Fragment() {
    private var _binding: FragmentStickerBinding? = null
    private val binding: FragmentStickerBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStickerBinding.inflate(inflater, container, false)
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
