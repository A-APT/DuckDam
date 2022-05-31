package com.aligatorapt.duckdam.view.fragment.setting

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aligatorapt.duckdam.databinding.FragmentSettingBinding
import com.aligatorapt.duckdam.sharedpreferences.MyApplication
import com.aligatorapt.duckdam.viewModel.LoginSingleton

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding: FragmentSettingBinding get() = _binding!!

    private val model = LoginSingleton.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.apply {
            if(model!!.user.value!!.profile != null){
                val decodedImageBytes: ByteArray = Base64.decode(model.user.value!!.profile, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(decodedImageBytes, 0, decodedImageBytes.size)
                sticker.setImageBitmap(bitmap)
            }
            rowStickerName.text = model.user.value!!.name
            settingEmail.text = MyApplication.prefs.getString("email", "notExist")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
