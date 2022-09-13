package com.example.kmmsamplelogin.commonRemote

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import java.util.concurrent.TimeUnit

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(OkHttp) {
    config(this)

    engine {
        config {
            retryOnConnectionFailure(true)
            connectTimeout(5, TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES)
            readTimeout(2, TimeUnit.MINUTES)
        }
    }
}