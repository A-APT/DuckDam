package com.aligatorapt.duckdam.retrofit.callback

import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto

interface ComplimentsCallback {
    //여러개의 칭찬 response
    fun complimentsCallback(flag: Boolean, data: ArrayList<ComplimentResponseDto>?)
}
