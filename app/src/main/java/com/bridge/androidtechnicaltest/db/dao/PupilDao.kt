package com.bridge.androidtechnicaltest.db.dao;

import androidx.paging.PagingSource
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.bridge.androidtechnicaltest.db.PupilEntity

@Dao
interface PupilDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pupil: List<PupilEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pupil: PupilEntity)

    @Query("SELECT * FROM Pupils ORDER BY name ASC")
    fun pagingSource(): PagingSource<Int, PupilEntity>

    @Query("SELECT * FROM Pupils WHERE is_synced = 0")
    suspend fun getUnsyncedPupils(): List<PupilEntity>

    @Query("SELECT * FROM Pupils WHERE pupil_id = :id LIMIT 1")
    suspend fun getPupilById(id: String): PupilEntity?

    @Query("UPDATE Pupils SET is_synced = 1 WHERE pupil_id = :pupilId")
    suspend fun markAsSynced(pupilId: Int)

    @Query("UPDATE Pupils SET pupil_id = :newId, is_synced = 1 WHERE pupil_id = :oldId")
    suspend fun updatePupilId(oldId: Int, newId: Int)

    @Query("DELETE FROM Pupils WHERE is_synced = 1")
    suspend fun clearSyncedPupils()

    @Query("SELECT MAX(page) FROM Pupils")
    suspend fun getLastPageNumber(): Int?
}
