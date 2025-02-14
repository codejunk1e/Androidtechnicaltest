package com.bridge.androidtechnicaltest.db

import android.content.Context
import androidx.work.*
import com.bridge.androidtechnicaltest.network.PupilApi
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PupilSyncWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val database: AppDatabase,
    private val pupilApi: PupilApi
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        val unsyncedPupils = database.pupilDao.getUnsyncedPupils()

        if (unsyncedPupils.isEmpty()) return@coroutineScope Result.success()

        val failedPupils = mutableListOf<Int>()

        unsyncedPupils.forEach { pupil ->
            try {
                pupil.pupilId?.let { localId ->
                    if (pupil.isNew) {
                        val response = pupilApi.createPupil(pupil.toDto())
                        database.pupilDao.updatePupilId(localId, response.pupilId!!)
                    } else {
                        pupilApi.updatePupil(localId.toString(), pupil.toDto())
                        database.pupilDao.markAsSynced(localId)
                    }
                }
            } catch (e: Exception) {
                failedPupils.add(pupil.pupilId ?: -1)
            }
        }

        return@coroutineScope if (failedPupils.isNotEmpty()) {
            Result.retry()
        } else {
            Result.success()
        }
    }
}

class PupilWorkerFactory @Inject constructor(
    private val database: AppDatabase,
    private val pupilApi: PupilApi
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            PupilSyncWorker::class.java.name ->
                PupilSyncWorker(appContext, workerParameters, database, pupilApi)
            else -> null
        }
    }
}