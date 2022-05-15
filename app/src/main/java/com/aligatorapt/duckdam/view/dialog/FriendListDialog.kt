package com.aligatorapt.duckdam.view.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.DialogFriendlistBinding
import com.aligatorapt.duckdam.view.adapter.SelectListAdapter
import com.aligatorapt.duckdam.view.data.SelectList


class FriendListDialog: DialogFragment() {
    private var _binding: DialogFriendlistBinding ?= null
    private val binding : DialogFriendlistBinding get() = _binding!!

    private var header = ""
    var array : ArrayList<SelectList> = setStickerList()
    private var isSticker = false
    var itemClickListener: OnItemClickListener ?= null

    interface OnItemClickListener{
        fun OnItemClick(isSticker: Boolean, data: SelectList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 화면 밖 클릭시 다이얼로그 dismiss
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogFriendlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            header = arguments?.getString("title").toString()
            Log.e("HEADER",header.toString())
            if(header == "스티커 고르기"){
                isSticker = true
                array = setStickerList()
            } else if(header == "친구 목록"){
                isSticker = false
                array = setFriendList()
            }
            title.text = header

            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            friendlist.layoutManager = linearLayoutManager
            val selectAdapter = SelectListAdapter(requireContext(),array,false)
            selectAdapter.itemClickListener = object: SelectListAdapter.OnItemClickListener{
                override fun OnItemClick(data: SelectList, position: Int) {
                    itemClickListener?.OnItemClick(isSticker,data)
                    dismiss()
                }
            }

            friendlist.adapter = selectAdapter
            closeBtn.setOnClickListener { dismiss() }
        }
    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker05,
                name = "고구마",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.sticker06,
                name = "사랑니",
                isAdded = true
            )
        )
    }

    private fun setFriendList(): ArrayList<SelectList> {
        return arrayListOf(
            SelectList(
                sticker = R.drawable.small_sample,
                name = "어깨피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "허리피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "맛있는피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "파인애플피자",
                isAdded = true
            ),
            SelectList(
                sticker = R.drawable.small_sample,
                name = "민트초코쿠키",
                isAdded = true
            )
        )
    }
}