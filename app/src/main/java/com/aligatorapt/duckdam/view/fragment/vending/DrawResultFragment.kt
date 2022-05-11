package com.aligatorapt.duckdam.view.fragment.vending

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentDrawResultBinding
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.activity.VendingActivity
import com.aligatorapt.duckdam.view.dialog.ModalDialog

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
        val mActivity = activity as VendingActivity
        binding.apply {
            showContentBtn.setOnClickListener {
                //다이얼로그
                val bundle = Bundle()
                bundle.putString("title", "오늘의 칭찬")
                bundle.putString("message", "나무는 얼마나 땅 깊숙이 뿌리를 박고 있을까요? 저 나무도 바람에 흔들리는데 사람 마음이 흔들리는 건 당연한 것입니다. 오늘도 수고했어요:)")

                val modalDialog = ModalDialog()
                modalDialog.arguments = bundle
                modalDialog.itemClickListener = object : ModalDialog.OnItemClickListener{
                    override fun OnCloseBotton() {
                        modalDialog.dismiss()
                    }
                }
                modalDialog.show(mActivity.supportFragmentManager, "ModalDialog")
            }

            myStickerBtn.setOnClickListener {
                val intent = Intent(mActivity, NavigationActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.putExtra("position", R.id.tab_sticker)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
