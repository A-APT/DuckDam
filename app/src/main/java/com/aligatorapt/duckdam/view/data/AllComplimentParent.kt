package com.aligatorapt.duckdam.view.data

import com.aligatorapt.duckdam.dto.compliment.ComplimentResponseDto
import java.util.*

data class AllComplimentParent(
    val date : String,
    val sticker : ArrayList<ComplimentResponseDto>,
)
