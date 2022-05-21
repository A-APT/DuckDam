package com.aligatorapt.duckdam.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.dto.user.LoginRequestDto
import com.aligatorapt.duckdam.model.UserModel
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    fun login(_loginRequestDto: LoginRequestDto, callback: ApiCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                UserModel.login(_loginRequestDto, callback)
            }
        }
    }
}