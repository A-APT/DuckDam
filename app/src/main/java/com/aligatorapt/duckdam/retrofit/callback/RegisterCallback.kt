package com.aligatorapt.duckdam.retrofit.callback

interface RegisterCallback {
    //isNickname : 닉네임 중복 여부
    fun registerCallback(flag: Boolean, isNickname: Boolean)
}