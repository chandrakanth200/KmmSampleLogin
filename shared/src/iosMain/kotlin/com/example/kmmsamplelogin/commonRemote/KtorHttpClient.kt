package com.example.kmmsamplelogin.commonRemote

import io.ktor.client.*
import io.ktor.client.engine.ios.*
import io.ktor.client.features.*
//import io.ktor.client.request.*
//import kotlinx.coroutines.withTimeout

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Ios) {
    config(this)
    install(HttpTimeout) {
        connectTimeoutMillis = 300000
        requestTimeoutMillis = 300000
        socketTimeoutMillis  = 300000
    }
    engine {
        configureRequest {


        }
    }
}