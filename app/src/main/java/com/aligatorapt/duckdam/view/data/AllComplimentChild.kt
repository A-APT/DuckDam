package com.aligatorapt.duckdam.view.data

import java.io.Serializable

data class AllComplimentChild(
    val sticker : Int,
    val date: String,
    val content: String,
    val from: String,
): Serializable
