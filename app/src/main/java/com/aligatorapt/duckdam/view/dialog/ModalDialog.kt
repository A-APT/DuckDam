package com.aligatorapt.duckdam.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.aligatorapt.duckdam.databinding.DialogModalBinding

class ModalDialog: DialogFragment() {
    private var _binding: DialogModalBinding? = null
    private val binding: DialogModalBinding get() = _binding!!

    private var _title = ""
    private var _message = ""

    interface OnItemClickListener{
        fun OnCloseBotton()
    }

    var itemClickListener: OnItemClickListener ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogModalBinding.inflate(inflater, container, false)

        _title = arguments?.getString("title").toString()
        _message = arguments?.getString("message").toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            setMessage.text = _message
            setTitle.text = _title
            closeBtn.setOnClickListener {
                itemClickListener?.OnCloseBotton()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
