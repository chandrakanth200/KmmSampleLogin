package com.example.kmmsamplelogin.commonRepositories


import com.example.kmmsamplelogin.commonModels.*
import com.example.kmmsamplelogin.commonRemote.RemoteService
import io.ktor.client.features.*
import io.ktor.client.request.*

open class RepositorySDK(
    val remoteService: RemoteService
) {


    @Throws(Exception::class)
    suspend fun getUser(): GetUserResponse {
        return remoteService.httpClient.get{
            url(remoteService.getUrl("api/users/2"))
            /*headers {
                remoteService.addHeaders(this, false)
            }*/
        }
    }


    @Throws(Exception::class)
    suspend fun generateLoginToken(product : String, publicKey : String): GenerateTokenResponse {
        val payload = GenerateTokenRequest(product,publicKey)
        return remoteService.httpClient.post {
            url(remoteService.getUrl("service/generate-token"))
            headers {
                remoteService.addHeaders(this, false)
            }
            body = payload
        }
    }

    @Throws(Exception::class)
    suspend fun login(loginRequest: LoginRequest) : LoginResponse {
        return remoteService.httpClient.post {
            url(remoteService.getUrl("service/login"))
            headers {
                remoteService.addHeaders(this, false)
            }
            body = loginRequest
        }
    }

}