package com.bridge.androidtechnicaltest

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bridge.androidtechnicaltest.db.AppDatabase
import com.bridge.androidtechnicaltest.db.PupilEntity
import com.bridge.androidtechnicaltest.db.dao.PupilDao
import com.bridge.androidtechnicaltest.domain.Pupil
import com.bridge.androidtechnicaltest.domain.PupilRepository
import com.bridge.androidtechnicaltest.network.PupilApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PupilRepositoryTest {
//
//    private lateinit var repository: PupilRepository
//    private val database: AppDatabase = mockk(relaxed = true)
//    private val pupilDao: PupilDao = mockk(relaxed = true)
//    private val pupilApi: PupilApi = mockk(relaxed = true)
//
//    @Before
//    fun setup() {
//        every { database.pupilDao } returns pupilDao
//        repository = PupilRepository(mockk(), database, pupilApi)
//    }
//
//    @Test
//    fun `savePupil inserts new pupil and triggers sync`() = runBlocking {
//        val pupil = Pupil(1, "Test", "Country", "url.com")
//        repository.savePupil(pupil)
//        coVerify { pupilDao.insert(any<PupilEntity>()) }
//    }
//
//    @Test
//    fun `synchronizeData updates pupil IDs after successful sync`() = runBlocking {
//        val pupilEntity = PupilEntity(0, "Test", "Country", "url.com", 0.0, 0.0, isNew = true)
//        coEvery { pupilDao.getUnsyncedPupils() } returns listOf(pupilEntity)
//        coEvery { pupilApi.createPupil(any()) } returns PupilDto(1, "Test", "Country", "url.com", 0.0, 0.0)
//
//        repository.synchronizeData()
//
//        coVerify { pupilDao.updatePupilId(0, 1) }
//    }
}
