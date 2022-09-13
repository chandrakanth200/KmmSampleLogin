package com.example.kmmsamplelogin.commonModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateTokenResponse(

    @SerialName("hsalt")
    val hsalt: String,
    @SerialName("token")
    val token: String,
    @SerialName("status")
    val status: String,
    @SerialName("loginType")
    val loginType: String,
    @SerialName("publicKey")
    val publicKey: String

)
