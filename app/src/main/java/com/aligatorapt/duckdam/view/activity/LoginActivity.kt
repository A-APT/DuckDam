package com.aligatorapt.duckdam.view.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.aligatorapt.duckdam.R
import com.aligatorapt.duckdam.databinding.ActivityLoginBinding
import com.aligatorapt.duckdam.dto.user.LoginRequestDto
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.sharedpreferences.MyApplication
import com.aligatorapt.duckdam.viewModel.LoginViewModel

class LoginActivity: AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private val model: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            loginSignup.setOnClickListener {
                val intent = Intent(this@LoginActivity,SignUpActivity::class.java)
                startActivity(intent)
            }

            loginLoginTv.setOnClickListener {
                model.login(
                    LoginRequestDto(
                        email = loginEmail.text.toString(),
                        password = loginPw.text.toString()
                    ),object: ApiCallback{
                        override fun apiCallback(flag: Boolean) {
                            if(flag){
                                MyApplication.prefs.setString("email",loginEmail.text.toString())
                                val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this@LoginActivity, "회원 정보가 일치하지 않습니다.", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                )
            }
        }
    }
}