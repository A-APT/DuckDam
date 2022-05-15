package com.aligatorapt.duckdam.view.fragment.compliment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aligatorapt.duckdam.databinding.FragmentComplimentBinding
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.data.SelectList
import com.aligatorapt.duckdam.view.dialog.FriendListDialog

class ComplimentFragment : Fragment() {
    private var _binding: FragmentComplimentBinding? = null
    private val binding: FragmentComplimentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentComplimentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val mActivity = activity as NavigationActivity
        binding.apply {
            //다이얼로그
            val bundle = Bundle()
            edit.setOnClickListener {
                val friendListDialog = FriendListDialog()
                bundle.putString("title","스티커 고르기")
                friendListDialog.arguments = bundle
                friendListDialog.itemClickListener = object: FriendListDialog.OnItemClickListener{
                    override fun OnItemClick(isSticker: Boolean, data: SelectList) {
                        if(isSticker){
                            sticker.setImageResource(data.sticker)
                        }else{
                            receiver.text = data.name
                        }
                    }
                }
                friendListDialog.show(mActivity.supportFragmentManager, "FriendListDialog")
            }
            receiverLayout.setOnClickListener {
                val friendListDialog = FriendListDialog()
                bundle.putString("title","친구 목록")
                friendListDialog.arguments = bundle
                friendListDialog.itemClickListener = object: FriendListDialog.OnItemClickListener{
                    override fun OnItemClick(isSticker: Boolean, data: SelectList) {
                        if(isSticker){
                            sticker.setImageResource(data.sticker)
                        }else{
                            receiver.text = data.name
                        }
                    }
                }
                friendListDialog.show(mActivity.supportFragmentManager, "FriendListDialog")
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
