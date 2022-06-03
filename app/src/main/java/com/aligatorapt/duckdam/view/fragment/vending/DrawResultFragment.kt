package com.aligatorapt.duckdam.view.fragment.vending

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.FragmentDrawResultBinding
import com.aligatorapt.duckdam.view.activity.NavigationActivity
import com.aligatorapt.duckdam.view.activity.VendingActivity
import com.aligatorapt.duckdam.view.dialog.ModalDialog
import com.aligatorapt.duckdam.viewModel.VendingViewModel

class DrawResultFragment : Fragment() {
    private var _binding: FragmentDrawResultBinding? = null
    private val binding: FragmentDrawResultBinding get() = _binding!!
    private val model: VendingViewModel by activityViewModels()

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
            model.slotResult.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    stickerName.text = "안녕? 난 ${resources.getStringArray(R.array.sticker)[it.stickerNum]}!"
                    stickerImg.setImageResource(resources.obtainTypedArray(R.array.stickerImg).getResourceId(it.stickerNum, 0))
                }
            })
            showContentBtn.setOnClickListener {
                //다이얼로그
                val bundle = Bundle()
                bundle.putString("title", "오늘의 칭찬")
                model.slotResult.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        bundle.putString("message", it.message)
                    }
                })

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
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
