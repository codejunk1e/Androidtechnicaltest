package com.bridge.androidtechnicaltest.di
import com.bridge.androidtechnicaltest.db.AppDatabase
import com.bridge.androidtechnicaltest.db.PupilWorkerFactory
import com.bridge.androidtechnicaltest.domain.IPupilRepository
import com.bridge.androidtechnicaltest.network.PupilApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestRepositoryModule {

    @Provides
    @Singleton
    fun provideFakePupilRepository(): IPupilRepository {
        return FakePupilRepository()
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
