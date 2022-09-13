package com.example.kmmsamplelogin.commonRemote

import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

open class RemoteService(val enableLog: Boolean) {
    //private val baseUrl = "https://dil2uat.bankofbaroda.co.in/APIGateway/um/"
    private val baseUrl = "https://reqres.in/"

    var httpClient = httpClient {
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
            serializer = KotlinxSerializer(json)
        }

        if (enableLog) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.ALL
            }
        }
    }


    @Throws(Exception::class)
    fun getUrl(endPoint: String): Url = URLBuilder(baseUrl  + endPoint).build()


    @Throws(Exception::class)
    fun addHeaders(headersBuilder: HeadersBuilder, isRequireDynamicToken: Boolean) {
        headersBuilder.apply {
            append("Content-Type", "application/json")
        }
    }

}

