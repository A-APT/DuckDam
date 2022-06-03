package com.aligatorapt.duckdam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.model.UserModel
import com.aligatorapt.duckdam.retrofit.callback.BooleanListCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StickerViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    val stickers = MutableLiveData<List<Boolean>?>()

    fun setSticker(_data: ArrayList<Boolean>?){
        stickers.value = _data
    }


    fun getStickerList(callback: BooleanListCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                UserModel.getStickerList(callback)
            }
        }
    }
}

object StickerSingleton  {
    private var model: StickerViewModel? = null

    fun getInstance(): StickerViewModel? {
        if (model == null) {
            model = StickerViewModel()
        }
        return model
    }
}