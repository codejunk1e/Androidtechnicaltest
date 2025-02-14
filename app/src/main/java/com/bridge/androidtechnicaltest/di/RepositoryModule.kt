package com.bridge.androidtechnicaltest.di

import android.content.Context
import com.bridge.androidtechnicaltest.db.AppDatabase
import com.bridge.androidtechnicaltest.db.PupilWorkerFactory
import com.bridge.androidtechnicaltest.domain.IPupilRepository
import com.bridge.androidtechnicaltest.domain.PupilRepository
import com.bridge.androidtechnicaltest.network.PupilApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRepositoryModule(
        @ApplicationContext applicationContext: Context,
        database: AppDatabase,
        pupilApi: PupilApi
    ): IPupilRepository {
        return PupilRepository(applicationContext, database, pupilApi)
    }

    @Provides
    @Singleton
    fun provideWorkerFactory(
        database: AppDatabase,
        pupilApi: PupilApi
    ): PupilWorkerFactory {
        return PupilWorkerFactory(database, pupilApi)
    }

}