package com.aligatorapt.duckdam.view.fragment.sticker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentStickerBinding
import com.aligatorapt.duckdam.view.adapter.StickerAdapter
import com.aligatorapt.duckdam.view.data.SelectList

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
        binding.apply {
            val gridLayoutManger = GridLayoutManager(requireContext(),2)
            gridLayoutManger.orientation = GridLayoutManager.VERTICAL
            stickerRv.layoutManager = gridLayoutManger
            val stickerAdapter = StickerAdapter(requireContext(), setStickerList())
            stickerRv.adapter = stickerAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setStickerList(): ArrayList<SelectList> {
        return arrayListOf(
            SelectList(
                sticker = R.drawable.sticker01,
                name = "라우디",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker02,
                name = "장미콩",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker03,
                name = "단무지",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker04,
                name = "빈",
                isAdded = false
            ),
            SelectList(
                sticker = R.drawable.sticker05,
                name = "고구마",
                isAdded = false
            ),
            SelectList(
                sticker = R.drawable.sticker06,
                name = "사랑니",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker07,
                name = "보라뿔",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker08,
                name = "꿀벌복숭",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker09,
                name = "동동",
                isAdded = false
            ),
            SelectList(
                sticker = R.drawable.sticker10,
                name = "엄지공주",
                isAdded = false
            ),
        )
    }
}
