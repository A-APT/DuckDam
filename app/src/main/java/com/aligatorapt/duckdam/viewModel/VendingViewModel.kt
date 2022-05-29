package com.aligatorapt.duckdam.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import com.aligatorapt.duckdam.model.ComplimentModel
import com.aligatorapt.duckdam.model.UserModel
import com.aligatorapt.duckdam.retrofit.callback.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VendingViewModel: ViewModel() {
    private var dispatcher: CoroutineDispatcher = Dispatchers.IO

    val slotResult = MutableLiveData<ComplimentResponseDto>()

    fun setSlot(_data: ComplimentResponseDto){
        slotResult.value = _data
    }

    fun slot(callback: ComplimentCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                ComplimentModel.slot(callback)
            }
        }
    }

    fun isEligibleForSlot(callback: BooleanCallback){
        viewModelScope.launch {
            withContext(dispatcher){
                UserModel.isEligibleForSlot(callback)
            }
        }
    }
}
