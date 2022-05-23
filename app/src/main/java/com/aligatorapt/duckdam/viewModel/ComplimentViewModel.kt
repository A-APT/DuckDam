package com.aligatorapt.duckdam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.model.ComplimentModel
import com.aligatorapt.duckdam.retrofit.callback.ComplimentsCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComplimentViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    val compliments = MutableLiveData<List<ComplimentResponseDto>?>()
    val todayCompliments = MutableLiveData<List<ComplimentResponseDto>?>()

    fun setCompliments(_data: List<ComplimentResponseDto>){
        compliments.value = _data
    }

    fun setTodayCompliments(_data: List<ComplimentResponseDto>){
        todayCompliments.value = _data
    }

    fun findCompliments(callback: ComplimentsCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                ComplimentModel.findCompliments(callback)
            }
        }
    }
}

object ComplimentSingleton  {
    private var model: ComplimentViewModel? = null

    fun getInstance(): ComplimentViewModel? {
        if (model == null) {
            model = ComplimentViewModel()
        }
        return model
    }
}
