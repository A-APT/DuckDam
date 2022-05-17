package com.aligatorapt.duckdam.view.fragment.compliment

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentComplimentBinding
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.data.SelectList
import com.aligatorapt.duckdam.view.dialog.FriendListDialog
import android.text.Editable

import android.text.TextWatcher
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap


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

        if(arguments != null){
            val selectList = arguments?.getSerializable("selectList") as SelectList
            Log.e("SELECTLIST",selectList.toString())
            binding.receiver.text = selectList.name
        }

        init()
    }

    private fun init() {
        val mActivity = activity as NavigationActivity
        binding.apply {

            //content textwatcher
            content.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    val input: String = content.text.toString()
                    Log.e("TEXTWATCHER",input)
                    textnum.text = input.length.toString() + " / 200"
                }

                override fun afterTextChanged(s: Editable) {}
            })

            //다이얼로그
            edit.setOnClickListener {
                val bundle = Bundle()
                val friendListDialog = FriendListDialog()
                bundle.putString("title", "스티커 고르기")
                friendListDialog.arguments = bundle
                friendListDialog.itemClickListener = object : FriendListDialog.OnItemClickListener {
                    override fun OnItemClick(isSticker: Boolean, data: SelectList) {
                        if (isSticker) {
                            sticker.setImageResource(data.sticker)
                        } else {
                            receiver.text = data.name
                        }
                    }
                }
                friendListDialog.show(mActivity.supportFragmentManager, "FriendListDialog")
            }
            receiverLayout.setOnClickListener {
                val bundle = Bundle()
                val friendListDialog = FriendListDialog()
                bundle.putString("title", "친구 목록")
                friendListDialog.arguments = bundle
                friendListDialog.itemClickListener = object : FriendListDialog.OnItemClickListener {
                    override fun OnItemClick(isSticker: Boolean, data: SelectList) {
                        if (isSticker) {
                            sticker.setImageResource(data.sticker)
                        } else {
                            receiver.text = data.name
                        }
                    }
                }
                friendListDialog.show(mActivity.supportFragmentManager, "FriendListDialog")
            }

            sendBtn.setOnClickListener {

                if (receiver.text != "???" && sticker.drawable.constantState!! != ContextCompat.getDrawable(requireContext(), R.drawable.big_image)?.constantState && content.text.toString() != "") {
                    Toast.makeText(requireContext(), "칭찬이 전송되었습니다!", Toast.LENGTH_SHORT).show()
                    receiver.text = "???"
                    sticker.setImageResource(R.drawable.big_image)
                    content.setText("")
                }else {
                    Toast.makeText(requireContext(), "친구와 스티커, 칭찬을 입력해주세요.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
