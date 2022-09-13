package com.example.kmmsamplelogin.commonRemote

import io.ktor.client.*

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit): HttpClient