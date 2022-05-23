package com.aligatorapt.duckdam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.dto.compliment.ComplimentRequestDto
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.model.ComplimentModel
import com.aligatorapt.duckdam.retrofit.callback.ApiCallback
import com.aligatorapt.duckdam.retrofit.callback.ComplimentCallback
import com.aligatorapt.duckdam.retrofit.callback.ComplimentsCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComplimentViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    val compliments = MutableLiveData<ArrayList<ComplimentResponseDto>>()
    val todayCompliments = MutableLiveData<ArrayList<ComplimentResponseDto>>()


    fun setCompliments(_data: ArrayList<ComplimentResponseDto>){
        compliments.value?.clear()
        compliments.value?.addAll(_data)
    }

    fun setTodayCompliments(_data: ArrayList<ComplimentResponseDto>){
        todayCompliments.value?.clear()
        todayCompliments.value?.addAll(_data)
    }

    fun generateCompliment(_data: ComplimentRequestDto, callback: ApiCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                ComplimentModel.generateCompliment(_data, callback)
            }
        }
    }

    fun findCompliments(callback: ComplimentsCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                ComplimentModel.findCompliments(callback)
            }
        }
    }

    fun findComplimentsByFromAndTo(_toId: Long, callback: ComplimentsCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                ComplimentModel.findComplimentsByFromAndTo(_toId, callback)
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
