package com.aligatorapt.duckdam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.dto.user.UserResponseDto
import com.aligatorapt.duckdam.model.FriendModel
import com.aligatorapt.duckdam.model.UserModel
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.UserCallback
import com.aligatorapt.duckdam.retrofit.callback.UserListCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FriendViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    val friends = MutableLiveData<List<UserResponseDto>?>()

    fun setFriend(_data: ArrayList<UserResponseDto>?){
        friends.value = _data
    }

    fun followFriend(_targetId: Long, callback: ApiCallback){
        viewModelScope.launch{
            withContext(dispatcher){
                FriendModel.followFriend(_targetId,callback)
            }
        }
    }

    fun findMyFriend(callback: UserCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                FriendModel.findMyFriend(callback)
            }
        }
    }

    fun searchByName(_query: String, callback: UserListCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                UserModel.searchByName(_query, callback)
            }
        }
    }
}

object FriendSingleton  {
    private var model: FriendViewModel? = null

    fun getInstance(): FriendViewModel? {
        if (model == null) {
            model = FriendViewModel()
        }
        return model
    }
}