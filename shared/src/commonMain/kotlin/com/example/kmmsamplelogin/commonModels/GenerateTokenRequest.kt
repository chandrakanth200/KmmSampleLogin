package com.example.kmmsamplelogin.commonModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerateTokenRequest(
    @SerialName("product") val product: String,
    @SerialName("publicKey") val publicKey: String

)
