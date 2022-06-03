package com.aligatorapt.duckdam.view.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.DialogFriendlistBinding
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.retrofit.callback.BooleanListCallback
import com.aligatorapt.duckdam.retrofit.callback.UserCallback
import com.aligatorapt.duckdam.view.adapter.SelectListAdapter
import com.aligatorapt.duckdam.viewModel.FriendSingleton
import com.aligatorapt.duckdam.viewModel.StickerSingleton


class FriendListDialog: DialogFragment() {
    private var _binding: DialogFriendlistBinding ?= null
    private val binding : DialogFriendlistBinding get() = _binding!!
    private var header = ""
    var itemClickListener: OnItemClickListener ?= null

    private val model = FriendSingleton.getInstance()
    private val stickermodel = StickerSingleton.getInstance()

    interface OnItemClickListener{
        fun OnItemClick(isSticker: Boolean, data: UserResponseDto)
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
            val linearLayoutManager = LinearLayoutManager(requireContext())
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            friendlist.layoutManager = linearLayoutManager
            val selectAdapter = SelectListAdapter(requireContext(),arrayListOf())

            selectAdapter.itemClickListener = object: SelectListAdapter.OnItemClickListener{
                override fun OnItemClick(data: UserResponseDto, position: Int, isSticker: Boolean) {
                    itemClickListener?.OnItemClick(isSticker,data)
                    dismiss()
                }
            }

            friendlist.adapter = selectAdapter

            header = arguments?.getString("title").toString()
            Log.e("HEADER",header.toString())

            var list : ArrayList<UserResponseDto> = arrayListOf()
            if(header == "스티커 고르기"){
                stickermodel?.getStickerList(object: BooleanListCallback{
                    override fun booleanlistCallback(flag: Boolean, data: ArrayList<Boolean>?) {
                        if(flag && data!!.isNotEmpty()){
                            emptyResult.visibility = View.GONE
                            friendlist.visibility = View.VISIBLE

                            stickermodel.setSticker(data)
                            for(i in data.indices){
                                if(data[i]){
                                    val select = UserResponseDto(
                                        uid = 0,
                                        name = resources.getStringArray(R.array.sticker)[i].toString(),
                                        profile = i.toString()
                                    )
                                    list.add(select)
                                }
                            }
                            selectAdapter.setData(list, true)
                        }
                    }

                })
            } else if(header == "친구 목록"){
                model?.findMyFriend(object: UserCallback{
                    override fun userCallback(flag: Boolean, data: ArrayList<UserResponseDto>?) {
                        if(flag && data!!.isNotEmpty()){
                            emptyResult.visibility = View.GONE
                            friendlist.visibility = View.VISIBLE

                            model.setFriend(data)
                            selectAdapter.setData(data, false)
                        }else{
                            emptyResult.visibility = View.VISIBLE
                            friendlist.visibility = View.GONE
                        }
                    }
                })
            }
            title.text = header
            closeBtn.setOnClickListener { dismiss() }
        }
    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //디바이스 크기
        val windowManager = activity?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)

        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        val deviceHeight = size.y
        params?.width = (deviceWidth * 0.8).toInt()
        params?.height = (deviceHeight * 0.65).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}