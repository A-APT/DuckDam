package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivitySignupBinding
import com.aligatorapt.duckdam.dto.auth.EmailTokenDto
import com.aligatorapt.duckdam.dto.user.RegisterDto
import com.aligatorapt.duckdam.model.UserModel.register
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.RegisterCallback
import com.aligatorapt.duckdam.viewModel.RegisterViewModel
import java.lang.Exception
import java.util.regex.Pattern

class SignUpActivity: AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    private var arr = booleanArrayOf(false,false,false,false,false,false)
    private val PROFILE_IMAGE = 100
    private var isActivateBtn = false

    private val model: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        EmailCode()
        check()
        register()
    }

    private fun register() {
        binding.apply {
            signupFinal.setOnClickListener {
                if(isActivateBtn){
                    model.registser(
                        RegisterDto(
                            name = signupNickname.text.toString(),
                            password = signupPwEt.text.toString(),
                            email = model.email.value.toString(),
                            profile = model.profile.value
                        ), object : RegisterCallback {
                            override fun registerCallback(flag: Boolean, isNickname: Boolean) {
                                if (flag) {
                                    signupNicknameError.visibility = View.GONE
                                    val intent = Intent(this@SignUpActivity,LoginActivity::class.java)
                                    finish()
                                    startActivity(intent)
                                }else if(!flag && isNickname){
                                    signupNicknameError.visibility = View.VISIBLE
                                    arr[4] = false
                                    setIsActivateBtn()
                                }
                            }
                        }
                    )
                }
            }
        }
    }

    private fun EmailCode() {
        binding.apply {
            
            //인증코드 전송
            signupCodesend.setOnClickListener {
                model.generateEmailAuth(signupEmail.text.toString(), object: ApiCallback{
                    override fun apiCallback(flag: Boolean) {
                        if(flag){
                            model.setEmail(signupEmail.text.toString())
                        }
                    }
                })
            }

            //인증코드 확인
            signupCodeTv.setOnClickListener {
                if(arr[0]){
                    model.verifyEmailToken(
                        EmailTokenDto(
                            email = signupEmail.text.toString(),
                            token = signupCode.text.toString()
                        ),
                        object : ApiCallback {
                            override fun apiCallback(flag: Boolean) {
                                if (flag) {
                                    arr[1] = true
                                    signupEmailError.visibility = View.VISIBLE
                                    signupEmailError.text = "인증되었습니다."
                                } else {
                                    signupEmailError.visibility = View.VISIBLE
                                    signupEmailError.text = "인증코드가 맞지 않습니다."
                                    arr[1] = false
                                }
                                setIsActivateBtn()
                            }
                        })
                }
            }
        }
    }

    private fun check(){
        binding.apply {
            signupBackIv.setOnClickListener {
                finish()
            }

            //이메일 형식 확인
            signupEmail.doAfterTextChanged {
                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(signupEmail.text.toString()).matches()){
                    signupEmailError.visibility = View.VISIBLE
                    signupEmailError.text = "올바르지 않은 이메일 양식입니다."
                    arr[0] = false
                }else{
                    signupEmailError.visibility = View.GONE
                    arr[0] = true
                }
                setIsActivateBtn()
            }

            //비밀번호
            signupPwEt.doAfterTextChanged {
                arr[2] = checkPassword(signupPwEt.text.toString())
                setIsActivateBtn()
            }

            //비밀번호 확인
            signupPwCheckEt.doAfterTextChanged {
                arr[3] = signupPwEt.text.toString() == signupPwCheckEt.text.toString()
                if(!arr[3]){
                    signupPwcheckError.visibility = View.VISIBLE
                    signupPwcheckError.text = "비밀번호와 일치하지 않습니다."
                }else{
                    signupPwcheckError.visibility = View.GONE
                }
                setIsActivateBtn()
            }

            //닉네임
            signupNickname.doAfterTextChanged {
                if(signupNickname.text.toString() != ""){
                    arr[4] = true
                }
                setIsActivateBtn()
            }

            //프로필
            signupImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = MediaStore.Images.Media.CONTENT_TYPE
                startActivityForResult(intent, PROFILE_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PROFILE_IMAGE) {
            if (resultCode == RESULT_OK) {
                val currentImageUri = data?.data
                try {
                    currentImageUri?.let {
                        if (Build.VERSION.SDK_INT < 28) {
                            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, currentImageUri)
                            model.setProfile(bitmap.toString())
                            binding.signupImage.setImageBitmap(bitmap)
                            //string -> bytearray
                            // bytearray.contentToString()
                        } else {
                            val source = ImageDecoder.createSource(contentResolver, currentImageUri)
                            val bitmap = ImageDecoder.decodeBitmap(source)
                            model.setProfile(bitmap.toString())
                            binding.signupImage.setImageBitmap(bitmap)
                        }
                        arr[5] = true
                    }
                } catch (e: Exception) {
                    arr[5] = false
                    e.printStackTrace()
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show()
                arr[5] = false
            }
        }
        setIsActivateBtn()
    }

    private fun checkPassword(password: String): Boolean {
        //숫자, 특수문자 포함
        val pwdRegex1 = "([0-9].*[!@#^&*()])|([!@#^&*()].*[0-9])"
        //영문자 대소문자가 적어도 하나이상 포함
        val pwdRegex2 = "([a-z].*[A-Z])|([A-Z].*[a-z])"

        val matcher1 = Pattern.compile(pwdRegex1).matcher(password)
        val matcher2 = Pattern.compile(pwdRegex2).matcher(password)

        //유효성 체크
        return if (password.length >= 8 && matcher1.find() && matcher2.find()) {
            binding.signupPwError.visibility = View.GONE
            true
        } else {
            binding.signupPwError.visibility = View.VISIBLE
            false
        }
    }

    private fun setIsActivateBtn(){
        Log.e("SETISACTIVE",arr[0].toString()+arr[1].toString()+arr[2].toString()+arr[3].toString()+arr[4].toString()+arr[5].toString())
        isActivateBtn = true
        for(element in arr){
            if(!element){
                isActivateBtn = false
                break
            }
        }
        if(isActivateBtn){
            binding.signupFinal.isClickable = true
            binding.signupFinal.setBackgroundColor(ContextCompat.getColor(this, R.color.main))
        }else {
            binding.signupFinal.isClickable = true
            binding.signupFinal.setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        }
    }
}