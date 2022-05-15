package com.aligatorapt.duckdam.view.data

import java.io.Serializable

data class SelectList (
    var sticker : Int,
    var name : String,
    var isAdded : Boolean
): Serializable