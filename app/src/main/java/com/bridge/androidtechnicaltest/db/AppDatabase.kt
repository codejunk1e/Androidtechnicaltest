package com.bridge.androidtechnicaltest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bridge.androidtechnicaltest.db.dao.PupilDao

@Database(entities = [PupilEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val pupilDao: PupilDao
}