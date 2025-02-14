package com.bridge.androidtechnicaltest.di
import androidx.paging.PagingData
import com.bridge.androidtechnicaltest.domain.IPupilRepository
import com.bridge.androidtechnicaltest.domain.Pupil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakePupilRepository : IPupilRepository {

    private val pupils = mutableListOf(
        Pupil(1, "John Doe", "Nigeria", "https://image.com/john.jpg"),
        Pupil(2, "Jane Smith", "Kenya", "https://image.com/jane.jpg")
    )

    private val _pupilsFlow = MutableStateFlow(PagingData.from(pupils))

    override fun getOrFetchPupils(): Flow<PagingData<Pupil>> = _pupilsFlow

    override suspend fun savePupil(newPupil: Pupil) {
        pupils.add(newPupil)
        _pupilsFlow.value = PagingData.from(pupils)
    }

    override suspend fun updatePupil(newPupil: Pupil) {
        val index = pupils.indexOfFirst { it.pupilId == newPupil.pupilId }
        if (index != -1) {
            pupils[index] = newPupil
            _pupilsFlow.value = PagingData.from(pupils)
        }
    }

    override suspend fun synchronizeData() {}
}
