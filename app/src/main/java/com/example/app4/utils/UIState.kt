package com.example.app4.utils

import com.example.app4.Model.JokesResponse

sealed class UIState{
    object LOADING : UIState()
    data class SUCCESS(val response: JokesResponse):UIState()
    data class ERROR(val error: Exception):UIState()
}
enum class STATE(val response :String) {
    LOADING(""),
    ERROR(""),
    SUCCESS("")
}