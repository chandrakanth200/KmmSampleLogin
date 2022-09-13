package com.example.kmmsamplelogin.commonModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("fullName")
    val fullName: String,
    @SerialName("idleLoginValidity")
    val idleLoginValidity: Int,
    @SerialName("name")
    val name: String,
    @SerialName("productId")
    val productId: Int,
    @SerialName("productName")
    val productName: String,
    @SerialName("roleId")
    val roleId: Int,
    @SerialName("roleName")
    val roleName: String,
    @SerialName("status")
    val status: String,
    @SerialName("token")
    val token: String,
    @SerialName("userId")
    val userId: Int
)
