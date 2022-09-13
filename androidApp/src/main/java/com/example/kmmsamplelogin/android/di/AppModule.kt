package com.example.kmmsamplelogin.android.di

import android.content.Context
import android.location.Geocoder
import com.example.kmmsamplelogin.android.BuildConfig
import com.example.kmmsamplelogin.commonRemote.RemoteService
import com.example.kmmsamplelogin.commonRepositories.RepositorySDK
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRemoteService(): RemoteService = RemoteService(
        enableLog = BuildConfig.DEBUG
    )

    @Provides
    @Singleton
    fun provideRepositorySDK(remoteService: RemoteService): RepositorySDK =
        RepositorySDK(remoteService = remoteService)

}