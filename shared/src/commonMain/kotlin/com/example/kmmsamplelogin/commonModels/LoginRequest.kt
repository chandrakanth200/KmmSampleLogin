package com.example.kmmsamplelogin.commonModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("loginToken")
    val loginToken: String,
    @SerialName("product")
    val product: String,
    @SerialName("uname")
    val uname: String,
    @SerialName("upwd")
    val upwd: String
)
