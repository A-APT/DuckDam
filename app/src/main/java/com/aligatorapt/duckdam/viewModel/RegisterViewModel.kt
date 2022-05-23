package com.aligatorapt.duckdam.viewModel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.dto.auth.EmailTokenDto
import com.aligatorapt.duckdam.dto.user.RegisterDto
import com.aligatorapt.duckdam.model.EmailModel
import com.aligatorapt.duckdam.model.UserModel
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.RegisterCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    val email = MutableLiveData<String>()
    val profile = MutableLiveData<String?>()

    fun setEmail(_email: String){
        email.value = _email
    }

    fun setProfile(_profile: String){
        profile.value = _profile
    }

    fun generateEmailAuth(_email: String, callback: ApiCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                EmailModel.generateEmailAuth(_email, callback)
            }
        }
    }

    fun verifyEmailToken(_emailTokenDto: EmailTokenDto, callback: ApiCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                EmailModel.verifyEmailToken(_emailTokenDto, callback)
            }
        }
    }

    fun registser(_userInfo: RegisterDto, callback: RegisterCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                UserModel.register(_userInfo, callback)
            }
        }
    }

}