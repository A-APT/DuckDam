package com.aligatorapt.duckdam.view.fragment.sticker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentStickerBinding
import com.aligatorapt.duckdam.retrofit.callback.BooleanListCallback
import com.aligatorapt.duckdam.view.adapter.StickerAdapter
import com.aligatorapt.duckdam.viewModel.StickerSingleton

class StickerFragment : Fragment() {
    private var _binding: FragmentStickerBinding? = null
    private val binding: FragmentStickerBinding get() = _binding!!

    private val stickermodel = StickerSingleton.getInstance()

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
            var list : ArrayList<Int> = arrayListOf()

            val gridLayoutManger = GridLayoutManager(requireContext(),2)
            gridLayoutManger.orientation = GridLayoutManager.VERTICAL
            stickerRv.layoutManager = gridLayoutManger
            val stickerAdapter = StickerAdapter(requireContext(), arrayListOf(), resources.getStringArray(R.array.sticker))
            stickerRv.adapter = stickerAdapter

            stickermodel?.getStickerList(object: BooleanListCallback {
                override fun booleanlistCallback(flag: Boolean, data: ArrayList<Boolean>?) {
                    if(flag && data != null){
                        stickermodel.setSticker(data)
                        stickerAdapter.setData(data)
                    }
                }
            })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
