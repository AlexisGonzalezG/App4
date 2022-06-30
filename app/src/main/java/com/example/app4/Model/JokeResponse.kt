package com.example.app4.Model


import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("type")
    val type: String?,
    @SerializedName("value")
    val value: Jokes
)