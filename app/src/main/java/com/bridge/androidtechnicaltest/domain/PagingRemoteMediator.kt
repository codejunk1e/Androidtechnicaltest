package com.bridge.androidtechnicaltest.domain

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.bridge.androidtechnicaltest.db.AppDatabase
import com.bridge.androidtechnicaltest.db.PupilEntity
import com.bridge.androidtechnicaltest.network.PupilApi
import com.bridge.androidtechnicaltest.network.toEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PagingRemoteMediator(
    private val database: AppDatabase,
    private val pupilApi: PupilApi
) : RemoteMediator<Int, PupilEntity>() {

    private val pupilDao = database.pupilDao

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PupilEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastPage = pupilDao.getLastPageNumber()
                    lastPage?.plus(1) ?: return MediatorResult.Success(endOfPaginationReached = true)
                }
            }

            val response = pupilApi.getPupils(page)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pupilDao.clearSyncedPupils()
                }

                val pupilsWithKeys = response.items.map { pupilResponse ->
                    pupilResponse.toEntity().copy(page = page)
                }

                pupilDao.insertAll(pupilsWithKeys)
            }

            MediatorResult.Success(endOfPaginationReached = response.pageNumber >= response.totalPages)

        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}