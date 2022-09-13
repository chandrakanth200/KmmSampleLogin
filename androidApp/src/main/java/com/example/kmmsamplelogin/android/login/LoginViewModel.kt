package com.example.kmmsamplelogin.android.login

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmmsamplelogin.android.BuildConfig
import com.example.kmmsamplelogin.android.utils.Resource
import com.example.kmmsamplelogin.android.utils.SessionStorage
import com.example.kmmsamplelogin.commonModels.GenerateTokenRequest
import com.example.kmmsamplelogin.commonModels.GenerateTokenResponse
import com.example.kmmsamplelogin.commonModels.LoginRequest
import com.example.kmmsamplelogin.commonModels.LoginResponse
import com.example.kmmsamplelogin.commonRepositories.RepositorySDK
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.libsodium.jni.SodiumConstants
import org.libsodium.jni.crypto.Random
import org.libsodium.jni.keys.KeyPair
import org.mindrot.jbcrypt.BCrypt
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Inject
import okhttp3.internal.and
import kotlin.system.measureTimeMillis

@HiltViewModel
class LoginViewModel @Inject constructor(val repository: RepositorySDK) : ViewModel() {

    var isEncrypted = false
    private var privateKey: String? = null
    private var serverPublicKey: String? = null
    private var serverSecurityKey: ByteArray? = null

    private val _generateTokenData = MutableLiveData<Resource<GenerateTokenResponse>>()
    val generateTokenData : LiveData<Resource<GenerateTokenResponse>> = _generateTokenData

    private val TAG = "LoginViewModel"
    private val _login = MutableLiveData<Resource<LoginResponse>>()
    val loginData : LiveData<Resource<LoginResponse>> = _login

    init {
        getUser()
      //  generateToken()
    }

    private fun getUser() {
        viewModelScope.launch {
            kotlin.runCatching {
                repository.getUser()
            }.onSuccess {
                Log.e(TAG, "getUser: ${it.data}", )
            }.onFailure {
                Log.e(TAG, "getUser: ${it.message}", )
            }
        }
    }

/*
    private fun generateToken() {
        viewModelScope.launch{
            _generateTokenData.postValue(Resource.Loading())
            kotlin.runCatching {

                    val publicKey = generatePublicKey()
                  */
/*  val generatePayload = GenerateTokenRequest("EL", publicKey)
                val time = measureTimeMillis {
                    repository.generateLoginToken(generatePayload)
                }*//*

               // Log.e(TAG, "generateToken: $time" )
                repository.generateLoginToken("EL", publicKey)
            }.onSuccess {
                if (it.status.lowercase()=="success"){
                    //_generateTokenData.postValue(Resource.Success(data = ))
                    login(it.token,it.hsalt)
                }
            }.onFailure {
                _generateTokenData.postValue(Resource.Error(it.message ))
            }
        }
    }
*/

/*
    fun login(s: String, hsalt: String) {
        viewModelScope.launch(Dispatchers.IO){
            _login.postValue(Resource.Loading())
            val sessionStorage = SessionStorage()
            kotlin.runCatching {
                val loginPayload = LoginRequest(
                    loginToken = s, product = "EL", uname = sessionStorage.USERNAME, upwd =
                    BCrypt.hashpw(
                        getSHA_512_SecurePassword(sessionStorage.PASSWORD) + hsalt,
                        BCrypt.gensalt()
                    )
                )
                repository.login(loginPayload)
            }.onSuccess {
                it.takeIf { it.status.lowercase() == "success" }?.let {
                    _login.postValue(Resource.Success(data = it))
                    sessionStorage.token = it.token
                    sessionStorage.roleID = it.roleId
                    sessionStorage.userID = it.userId
                }

            }.onFailure {
                _login.postValue(Resource.Error(message = it.message))
            }
        }
    }
*/


    fun generatePublicKey(): String {
        val seed = Random().randomBytes(SodiumConstants.SECRETKEY_BYTES)
        val encryptionKeyPair = KeyPair(seed)
        encryptionKeyPair.publicKey.toString()
        privateKey = encryptionKeyPair.privateKey.toString()
        return encryptionKeyPair.publicKey.toString()
    }

    @SuppressLint("NewApi")
    fun getSHA_512_SecurePassword(passwordToHash: String): String? {
        var generatedPassword: String? = null
        try {
            val md: MessageDigest = MessageDigest.getInstance("SHA-512")
            val bytes: ByteArray = md.digest(passwordToHash.toByteArray(StandardCharsets.UTF_8))
            val sb = StringBuilder()
            for (i in bytes.indices) {
                sb.append(((bytes[i] and 0xff) + 0x100).toString(16).substring(1))
            }
            generatedPassword = sb.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return generatedPassword
    }



}