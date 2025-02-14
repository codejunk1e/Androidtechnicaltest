package com.bridge.androidtechnicaltest.di

import com.bridge.androidtechnicaltest.network.PupilApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun providePupilApi(): PupilApi {
        return PupilAPIFactory.retrofitPupil()
    }
}