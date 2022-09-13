package com.example.kmmsamplelogin.commonModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("IsSuccess")
    val isSuccess: Boolean? = false,
    @SerialName("ReturnType")
    val returnType: String?=null,
    @SerialName("ReturnValue")
    val returnValue: T?=null,
    @SerialName("ReturnValue1")
    val returnValue1: T?=null,
    @SerialName("ReturnValue2")
    val returnValue2: String?=null,
    @SerialName("Schema")
    val schema: String?=null,
    @SerialName("UTCDate")
    val utcDate: String?=null,
    @SerialName("msg")
    val msg: String?=null,
    @SerialName("token")
    val token: String?=null
)