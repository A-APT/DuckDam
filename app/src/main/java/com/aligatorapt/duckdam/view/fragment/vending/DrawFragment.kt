package com.aligatorapt.duckdam.view.fragment.vending

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.aligatorapt.duckdam.databinding.FragmentDrawBinding
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.retrofit.callback.BooleanCallback
import com.aligatorapt.duckdam.retrofit.callback.ComplimentCallback
import com.aligatorapt.duckdam.view.activity.VendingActivity
import com.aligatorapt.duckdam.viewModel.VendingViewModel

class DrawFragment : Fragment() {
    private var _binding: FragmentDrawBinding? = null
    private val binding: FragmentDrawBinding get() = _binding!!

    private val model: VendingViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDrawBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val mActivity = activity as VendingActivity
        binding.apply {
            drawBtn.setOnClickListener {
                model.isEligibleForSlot(object: BooleanCallback{
                    override fun booleanCallback(flag: Boolean, isEnable: Boolean?) {
                        if(flag){
                            Log.e("ISENABLE::", isEnable.toString())
                            if(isEnable == true){
                                model.slot(object: ComplimentCallback{
                                    override fun complimentCallback(
                                        flag: Boolean,
                                        data: ComplimentResponseDto?
                                    ) {
                                        if(flag){
                                            if (data != null) {
                                                model.setSlot(data)
                                                mActivity.changeFragment(DrawResultFragment())
                                            }
                                        }
                                    }
                                })
                            }else{
                                Toast.makeText(mActivity, "오늘 이미 참여했어요.\n내일 다시 참여해주세요.", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                })
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
