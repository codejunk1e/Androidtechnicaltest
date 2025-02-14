package com.bridge.androidtechnicaltest.domain

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.bridge.androidtechnicaltest.db.AppDatabase
import com.bridge.androidtechnicaltest.db.PupilSyncWorker
import com.bridge.androidtechnicaltest.network.PupilApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface IPupilRepository {
    fun getOrFetchPupils() : Flow<PagingData<Pupil>>
    suspend fun savePupil(newPupil: Pupil)
    suspend fun synchronizeData()
    suspend fun updatePupil(newPupil: Pupil)
}

class PupilRepository(
    private val context: Context,
    private val database: AppDatabase,
    private val pupilApi: PupilApi
): IPupilRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getOrFetchPupils(): Flow<PagingData<Pupil>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            remoteMediator = PagingRemoteMediator(database, pupilApi),
            pagingSourceFactory = { database.pupilDao.pagingSource() }
        ).flow
            .map { pagingData -> pagingData.map { it.toDomain() } }
    }

    override suspend fun savePupil(newPupil: Pupil) {
        database.pupilDao.insert(newPupil.toEntity().copy(isNew = true))
        synchronizeData()
    }

    override suspend fun updatePupil(newPupil: Pupil) {
        database.pupilDao.insert(newPupil.toEntity().copy(isNew = false))
        synchronizeData()
    }

    override suspend fun synchronizeData() {
        val syncRequest = OneTimeWorkRequestBuilder<PupilSyncWorker>().build()
        WorkManager.getInstance(context).enqueue(syncRequest)
    }
}