package com.aligatorapt.duckdam.retrofit.callback

import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto

interface ComplimentCallback {
    //단일 칭찬 response
    fun complimentCallback(flag: Boolean, data: ComplimentResponseDto?)
}
